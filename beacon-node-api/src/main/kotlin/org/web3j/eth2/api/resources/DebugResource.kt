/**
 * Eth2 Beacon Node API
 * API specification for the beacon node, which enables users to query and participate in Ethereum 2.0 phase 0 beacon chain.
 *
 * OpenAPI spec version: v0.12.2
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package org.web3j.eth2.api.resources

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