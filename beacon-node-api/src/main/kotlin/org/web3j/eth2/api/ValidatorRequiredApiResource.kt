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
package org.web3j.eth2.api

import org.web3j.eth2.api.schema.Body
import org.web3j.eth2.api.schema.Body1
import org.web3j.eth2.api.schema.Body5
import org.web3j.eth2.api.schema.Body6
import org.web3j.eth2.api.schema.GetAggregatedAttestationResponse
import org.web3j.eth2.api.schema.GetAttesterDutiesResponse
import org.web3j.eth2.api.schema.GetGenesisResponse
import org.web3j.eth2.api.schema.GetProposerDutiesResponse
import org.web3j.eth2.api.schema.GetSpecResponse
import org.web3j.eth2.api.schema.StateFork
import org.web3j.eth2.api.schema.StateValidator
import org.web3j.eth2.api.schema.GetSyncingStatusResponse
import org.web3j.eth2.api.schema.ProduceAttestationDataResponse
import org.web3j.eth2.api.schema.ProduceBlockResponse

class ValidatorRequiredApiResource(basePath: String = "{server_url}") : ApiClient(basePath) {

    /**
     * Subscribe to beacon node events
     * Provides endpoint to subscribe to beacon node Server-Sent-Events stream. Consumers should use [eventsource](https://html.spec.whatwg.org/multipage/server-sent-events.html#the-eventsource-interface) implementation to listen on those events.
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

    /**
     * Get aggregated attestation
     * Aggregates all attestations matching given attestation data root and slot
     * @param attestationDataRoot HashTreeRoot of AttestationData that validator want&#x27;s aggregated
     * @param slot
     * @return GetAggregatedAttestationResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getAggregatedAttestation(attestationDataRoot: String, slot: String): GetAggregatedAttestationResponse {
        val localVariableQuery: MultiValueMap = mapOf("attestation_data_root" to listOf("$attestationDataRoot"), "slot" to listOf("$slot"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/validator/aggregate_attestation", query = localVariableQuery
        )
        val response = request<GetAggregatedAttestationResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetAggregatedAttestationResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get attester duties
     * Requests the beacon node to provide a set of attestation duties, which should be performed by validators, for a particular epoch. Duties should only need to be checked once per epoch, however a chain reorganization (of &gt; MIN_SEED_LOOKAHEAD epochs) could occur, resulting in a change of duties. For full safety, you should monitor chain reorganizations events.
     * @param body An array of the validator indices for which to obtain the duties.
     * @param epoch Should only be allowed 1 epoch ahead
     * @return GetAttesterDutiesResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getAttesterDuties(body: Array<String>, epoch: String): GetAttesterDutiesResponse {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/eth/v1/validator/duties/attester/{epoch}".replace("{" + "epoch" + "}", "$epoch")
        )
        val response = request<GetAttesterDutiesResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetAttesterDutiesResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Retrieve details of the chain&#x27;s genesis.
     * Retrieve details of the chain&#x27;s genesis which can be used to identify chain.
     * @return GetGenesisResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getGenesis(): GetGenesisResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/beacon/genesis"
        )
        val response = request<GetGenesisResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetGenesisResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get block proposers duties
     * Request beacon node to provide all validators that are scheduled to propose a block in the given epoch
     * @param epoch
     * @return GetProposerDutiesResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getProposerDuties(epoch: String): GetProposerDutiesResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/validator/duties/proposer/{epoch}".replace("{" + "epoch" + "}", "$epoch")
        )
        val response = request<GetProposerDutiesResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetProposerDutiesResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get spec params.
     * Retrieve specification configuration used on this node. [Specification params list](https://github.com/ethereum/eth2.0-specs/blob/v1.0.0-rc.0/configs/mainnet/phase0.yaml)  Values are returned with following format:   - any value starting with 0x in the spec is returned as a hex string   - numeric values are returned as a quoted integer
     * @return GetSpecResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getSpec(): GetSpecResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/config/spec"
        )
        val response = request<GetSpecResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetSpecResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get Fork object for requested state
     * Returns [Fork](https://github.com/ethereum/eth2.0-specs/blob/v0.11.1/specs/phase0/beacon-chain.md#fork) object for state with given &#x27;stateId&#x27;.
     * @param stateId State identifier. Can be one of: \&quot;head\&quot; (canonical head in node&#x27;s view), \&quot;genesis\&quot;, \&quot;finalized\&quot;, \&quot;justified\&quot;, \\&lt;slot\\&gt;, \\&lt;hex encoded stateRoot with 0x prefix\\&gt;.
     * @return GetStateForkResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getStateFork(stateId: String): StateFork {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/beacon/states/{state_id}/fork".replace("{" + "state_id" + "}", "$stateId")
        )
        val response = request<StateFork>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as StateFork
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
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
    fun getStateValidator(stateId: String, validatorId: String): StateValidator {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/beacon/states/{state_id}/validators/{validator_id}".replace("{" + "state_id" + "}", "$stateId").replace("{" + "validator_id" + "}", "$validatorId")
        )
        val response = request<StateValidator>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as StateValidator
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get node syncing status
     * Requests the beacon node to describe if it&#x27;s currently syncing or not, and if it is, what block it is up to.
     * @return GetSyncingStatusResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getSyncingStatus(): GetSyncingStatusResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/node/syncing"
        )
        val response = request<GetSyncingStatusResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetSyncingStatusResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Signal beacon node to prepare for a committee subnet
     * After beacon node receives this request, search using discv5 for peers related to this subnet and replace current peers with those ones if necessary If validator &#x60;is_aggregator&#x60;, beacon node must: - announce subnet topic subscription on gossipsub - aggregate attestations received on that subnet
     * @param body (optional)
     * @return void
     */
    fun prepareBeaconCommitteeSubnet(body: Array<Body6>? = null) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/eth/v1/validator/beacon_committee_subscriptions"
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Produce an attestation data
     * Requests that the beacon node produce an AttestationData.
     * @param slot The slot for which an attestation data should be created.
     * @param committeeIndex The committee index for which an attestation data should be created.
     * @return ProduceAttestationDataResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun produceAttestationData(slot: String, committeeIndex: String): ProduceAttestationDataResponse {
        val localVariableQuery: MultiValueMap = mapOf("slot" to listOf("$slot"), "committee_index" to listOf("$committeeIndex"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/validator/attestation_data", query = localVariableQuery
        )
        val response = request<ProduceAttestationDataResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProduceAttestationDataResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Produce a new block, without signature.
     * Requests a beacon node to produce a valid block, which can then be signed by a validator.
     * @param slot The slot for which the block should be proposed.
     * @param randaoReveal The validator&#x27;s randao reveal value.
     * @param graffiti Arbitrary data validator wants to include in block. (optional)
     * @return ProduceBlockResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun produceBlock(slot: String, randaoReveal: String, graffiti: String? = null): ProduceBlockResponse {
        val localVariableQuery: MultiValueMap = mapOf("randao_reveal" to listOf("$randaoReveal"), "graffiti" to listOf("$graffiti"))
        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/validator/blocks/{slot}".replace("{" + "slot" + "}", "$slot"), query = localVariableQuery
        )
        val response = request<ProduceBlockResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as ProduceBlockResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Publish multiple aggregate and proofs
     * Verifies given aggregate and proofs and publishes them on appropriate gossipsub topic.
     * @param body (optional)
     * @return void
     */
    fun publishAggregateAndProofs(body: Array<Body5>? = null) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/eth/v1/validator/aggregate_and_proofs"
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Publish a signed block.
     * Instructs the beacon node to broadcast a newly signed beacon block to the beacon network, to be included in the beacon chain. The beacon node is not required to validate the signed &#x60;BeaconBlock&#x60;, and a successful response (20X) only indicates that the broadcast has been successful. The beacon node is expected to integrate the new block into its state, and therefore validate the block internally, however blocks which fail the validation are still broadcast but a different status code is returned (202)
     * @param body The &#x60;SignedBeaconBlock&#x60; object composed of &#x60;BeaconBlock&#x60; object (produced by beacon node) and validator signature.
     * @return void
     */
    fun publishBlock(body: Body) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/eth/v1/beacon/blocks"
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Submit Attestation object to node
     * Submits Attestation object to node. If attestation passes all validation constraints, node MUST publish attestation on appropriate subnet.
     * @param body
     * @return void
     */
    fun submitPoolAttestations(body: Body1) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
                RequestMethod.POST,
                "/eth/v1/beacon/pool/attestations"
        )
        val response = request<Any?>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }
}
