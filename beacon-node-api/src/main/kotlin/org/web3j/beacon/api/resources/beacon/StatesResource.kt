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
package org.web3j.beacon.api.resources.beacon

import org.web3j.beacon.api.schema.GetEpochCommitteesResponse
import org.web3j.beacon.api.schema.GetStateFinalityCheckpointsResponse
import org.web3j.beacon.api.schema.GetStateForkResponse
import org.web3j.beacon.api.schema.GetStateRootResponse
import org.web3j.beacon.api.schema.GetStateValidatorBalancesResponse
import org.web3j.beacon.api.schema.GetStateValidatorResponse
import org.web3j.beacon.api.schema.GetStateValidatorsResponse
import org.web3j.beacon.api.schema.Index

interface StatesResource {

    /**
     * Get state finality checkpoints
     * Returns finality checkpoints for state with given &#x27;stateId&#x27;. In case finality is not yet achieved, checkpoint should return epoch 0 and ZERO_HASH as root.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @return GetStateFinalityCheckpointsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateFinalityCheckpoints(stateId: String): GetStateFinalityCheckpointsResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/finality_checkpoints".replace("{" + "state_id" + "}", "$stateId")
        )
        val response = request<GetStateFinalityCheckpointsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateFinalityCheckpointsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get Fork object for requested state
     * Returns [Fork](https://github.com/ethereum/eth2.0-specs/blob/v0.11.1/specs/phase0/beacon-chain.md#fork) object for state with given &#x27;stateId&#x27;.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @return GetStateForkResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateFork(stateId: String): GetStateForkResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/fork".replace("{" + "state_id" + "}", "$stateId")
        )
        val response = request<GetStateForkResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateForkResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get state SSZ HashTreeRoot
     * Calculates HashTreeRoot for state with given &#x27;stateId&#x27;. If stateId is root, same value will be returned.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @return GetStateRootResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateRoot(stateId: String): GetStateRootResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/root".replace("{" + "state_id" + "}", "$stateId")
        )
        val response = request<GetStateRootResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateRootResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get validator from state by id
     * Returns validator specified by state and id or public key along with status and balance.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @param validatorId Either hex encoded public key (with 0x prefix) or validator index
     * @return GetStateValidatorResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateValidator(stateId: String, validatorId: String): GetStateValidatorResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/validators/{validator_id}".replace("{" + "state_id" + "}", "$stateId")
                .replace("{" + "validator_id" + "}", "$validatorId")
        )
        val response = request<GetStateValidatorResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateValidatorResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get validator balances from state
     * Returns filterable list of validator balances.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional)
     * @return GetStateValidatorBalancesResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateValidatorBalances(stateId: String, id: Array<String>): GetStateValidatorBalancesResponse {
        val localVariableQuery: MultiValueMap = mapOf("id" to toMultiValue(id.toList(), "multi"))
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/validator_balances".replace("{" + "state_id" + "}", "$stateId"),
            query = localVariableQuery
        )
        val response = request<GetStateValidatorBalancesResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateValidatorBalancesResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get validators from state
     * Returns filterable list of validators with their balance, status and index.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional)
     * @param status [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ) (optional)
     * @return GetStateValidatorsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateValidators(stateId: String, id: Array<String>, status: Array<Any>): GetStateValidatorsResponse {
        val localVariableQuery: MultiValueMap =
            mapOf("id" to toMultiValue(id.toList(), "multi"), "status" to toMultiValue(status.toList(), "multi"))
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/validators".replace("{" + "state_id" + "}", "$stateId"),
            query = localVariableQuery
        )
        val response = request<GetStateValidatorsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetStateValidatorsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get all committees for epoch
     * Retrieves the committees for the given state at the given epoch.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     * @param index Committee index (optional)
     * @param slot (optional)
     * @return GetEpochCommitteesResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getEpochCommittees(
        stateId: String,
        epoch: Epoch,
        index: Index? = null,
        slot: Slot? = null
    ): GetEpochCommitteesResponse {
        val localVariableQuery: MultiValueMap = mapOf("index" to listOf("$index"), "slot" to listOf("$slot"))
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/states/{state_id}/committees/{epoch}".replace("{" + "state_id" + "}", "$stateId")
                .replace("{" + "epoch" + "}", "$epoch"), query = localVariableQuery
        )
        val response = request<GetEpochCommitteesResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetEpochCommitteesResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }
}
