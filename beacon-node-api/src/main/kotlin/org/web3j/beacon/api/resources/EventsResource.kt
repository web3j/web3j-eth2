/*
 * Copyright 2020 Web3 Labs Ltd.
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
package org.web3j.beacon.api.resources

import org.web3j.beacon.client.infrastructure.ApiClient
import org.web3j.beacon.client.infrastructure.ClientError
import org.web3j.beacon.client.infrastructure.ClientException
import org.web3j.beacon.client.infrastructure.MultiValueMap
import org.web3j.beacon.client.infrastructure.RequestConfig
import org.web3j.beacon.client.infrastructure.RequestMethod
import org.web3j.beacon.client.infrastructure.ResponseType
import org.web3j.beacon.client.infrastructure.ServerError
import org.web3j.beacon.client.infrastructure.ServerException
import org.web3j.beacon.client.infrastructure.Success
import org.web3j.beacon.client.infrastructure.toMultiValue

class EventsResource(basePath: String = "{server_url}") : ApiClient(basePath) {

    /**
     * Subscribe to beacon node events
     * Provides endpoint to subscribe to beacon node Server-Sent-Events stream.
     * Consumers should use [eventsource](https://html.spec.whatwg.org/multipage/server-sent-events.html#the-eventsource-interface)
     * implementation to listen on those events.
     * @param topics Event types to subscribe to
     * @return String
     */
    @Suppress("UNCHECKED_CAST")
    fun eventstream(topics: Array<String>): String {
        val localVariableQuery: MultiValueMap = mapOf("topics" to toMultiValue(topics.toList(), "multi"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/events", query = localVariableQuery
        )
        val response = request<String>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as String
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }
}
