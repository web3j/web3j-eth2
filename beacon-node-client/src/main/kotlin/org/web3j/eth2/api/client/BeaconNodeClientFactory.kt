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
package org.web3j.eth2.api.client

import org.glassfish.jersey.client.proxy.WebResourceFactory
import org.web3j.eth2.api.BeaconNodeApi
import java.lang.reflect.Proxy
import javax.ws.rs.client.ClientRequestContext
import javax.ws.rs.client.ClientRequestFilter
import javax.ws.rs.core.HttpHeaders

object BeaconNodeClientFactory {

    /**
     * Builds a JAX-RS client with the given service and optional token.
     */
    @JvmStatic
    @JvmOverloads
    fun build(service: BeaconNodeService, token: String? = null): BeaconNodeApi {
        val target = service.client.target(service.uri)
        token?.run { target.register(AuthenticationFilter(token)) }

        val client = WebResourceFactory.newResource(BeaconNodeApi::class.java, target)
        val handler = BeaconNodeClientInvocationHandler(target, client)

        @Suppress("UNCHECKED_CAST")
        return Proxy.newProxyInstance(
            BeaconNodeApi::class.java.classLoader,
            arrayOf(BeaconNodeApi::class.java),
            handler
        ) as BeaconNodeApi
    }

    private class AuthenticationFilter(private val token: String) : ClientRequestFilter {

        override fun filter(requestContext: ClientRequestContext) {
            requestContext.headers.putSingle(HttpHeaders.AUTHORIZATION, "Bearer $token")
        }
    }
}
