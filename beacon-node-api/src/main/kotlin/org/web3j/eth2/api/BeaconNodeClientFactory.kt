/*
 * Copyright 2019 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.eth2.api

import org.glassfish.jersey.client.proxy.WebResourceFactory
import java.lang.reflect.Proxy
import java.util.Base64
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.HttpHeaders

/**
 * Factory class for building [BeaconNodeApi] instances.
 *
 * It supports [Basic](https://tools.ietf.org/html/rfc7617) and
 * [Bearer](https://tools.ietf.org/html/rfc6750) authentication schemes.
 */
object BeaconNodeClientFactory {

    /**
     * Builds a [BeaconNodeApi] client with the given service and no authentication.
     *
     * @param service the node service instance.
     */
    @JvmStatic
    fun build(service: BeaconNodeService): BeaconNodeApi {
        return build(service.client.target(service.uri))
    }

    /**
     * Builds a [BeaconNodeApi] client with the given service and
     * [Bearer authentication scheme](https://tools.ietf.org/html/rfc6750).
     *
     * @param service the node service instance.
     * @param token the bearer authentication token.
     */
    @JvmStatic
    fun build(service: BeaconNodeService, token: String): BeaconNodeApi {
        return build(service.client.target(service.uri).register(OAuthFilter(token)))
    }

    /**
     * Builds a [BeaconNodeApi] client with the given service and
     * [Basic authentication scheme](https://tools.ietf.org/html/rfc7617).
     *
     * @param service the node service instance.
     * @param username the Basic authentication username.
     * @param password the Basic authentication password.
     */
    @JvmStatic
    fun build(service: BeaconNodeService, username: String, password: String): BeaconNodeApi {
        return build(service.client.target(service.uri).register(BasicAuthenticationFilter(username, password)))
    }

    private fun build(target: WebTarget): BeaconNodeApi {
        val client = WebResourceFactory.newResource(BeaconNodeApi::class.java, target)
        val handler = BeaconNodeClientInvocationHandler(target, client)

        @Suppress("UNCHECKED_CAST")
        return Proxy.newProxyInstance(
            BeaconNodeApi::class.java.classLoader,
            arrayOf(BeaconNodeApi::class.java),
            handler
        ) as BeaconNodeApi
    }

    private class BasicAuthenticationFilter(
        private val username: String,
        private val password: String
    ) : ClientRequestFilter {
        override fun filter(requestContext: ClientRequestContext) {
            val credentials = Base64.getEncoder().encodeToString("$username:$password".toByteArray())
            requestContext.headers.putSingle(HttpHeaders.AUTHORIZATION, "Basic $credentials")
        }
    }

    private class OAuthFilter(private val token: String) : ClientRequestFilter {
        override fun filter(requestContext: ClientRequestContext) {
            requestContext.headers.putSingle(HttpHeaders.AUTHORIZATION, "Bearer $token")
        }
    }
}
