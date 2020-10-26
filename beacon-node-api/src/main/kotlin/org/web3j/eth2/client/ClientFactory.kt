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
package org.web3j.eth2.client

import org.glassfish.jersey.client.proxy.WebResourceFactory
import org.web3j.eth2.client.infrastructure.AuthenticationFilter
import org.web3j.eth2.client.infrastructure.ClientInvocationHandler
import java.lang.reflect.Proxy

object ClientFactory {

    /**
     * Builds a JAX-RS client with the given service and optional token.
     */
    fun create(service: ClientService, token: String? = null): BeaconNodeApi {
        val target = service.client.target(service.uri)
        token?.run { target.register(AuthenticationFilter.token(token)) }

        val client = WebResourceFactory.newResource(BeaconNodeApi::class.java, target)
        val handler = ClientInvocationHandler(target, client)

        @Suppress("UNCHECKED_CAST")
        return Proxy.newProxyInstance(
            BeaconNodeApi::class.java.classLoader,
            arrayOf(BeaconNodeApi::class.java),
            handler
        ) as BeaconNodeApi
    }
}
