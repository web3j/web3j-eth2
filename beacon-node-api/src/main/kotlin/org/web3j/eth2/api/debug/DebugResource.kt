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
package org.web3j.eth2.api.debug

import org.web3j.eth2.client.infrastructure.ApiClient
import org.web3j.eth2.client.infrastructure.ClientError
import org.web3j.eth2.client.infrastructure.ClientException
import org.web3j.eth2.client.infrastructure.RequestConfig
import org.web3j.eth2.client.infrastructure.RequestMethod
import org.web3j.eth2.client.infrastructure.ResponseType
import org.web3j.eth2.client.infrastructure.ServerError
import org.web3j.eth2.client.infrastructure.ServerException
import org.web3j.eth2.client.infrastructure.Success
import org.web3j.eth2.client.models.GetDebugChainHeadsResponse
import org.web3j.eth2.client.models.GetStateResponse

class DebugResource(basePath: String = "{server_url}") : ApiClient(basePath) {

    /**
     * Get fork choice leaves
     * Retrieves all possible chain heads (leaves of fork choice tree).
     * @return GetDebugChainHeadsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getDebugChainHeads(): GetDebugChainHeadsResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/debug/beacon/heads"
        )
        val response = request<GetDebugChainHeadsResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetDebugChainHeadsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get full BeaconState object
     * Returns full BeaconState object for given stateId.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @return GetStateResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getState(stateId: String): GetStateResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/debug/beacon/states/{state_id}".replace("{" + "state_id" + "}", "$stateId")
        )
        val response = request<GetStateResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }
}
