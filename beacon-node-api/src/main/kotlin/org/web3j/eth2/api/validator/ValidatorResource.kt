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
package org.web3j.eth2.api.validator

import org.web3j.eth2.api.schema.Attestation
import org.web3j.eth2.api.schema.BeaconBlock
import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.ProduceAttestationDataResponse
import org.web3j.eth2.api.schema.Slot
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam

/**
 * Endpoints intended for validator clients.
 */
interface ValidatorResource {

    /**
     * Access the `duties` subresource.
     */
    @get:Path("duties")
    val duties: DutiesResource

    /**
     * Aggregates all attestations matching given attestation data root and slot.
     *
     * @param attestationDataRoot HashTreeRoot of AttestationData that validator want's aggregated.
     * @return Returns aggregated [Attestation] object with same [org.web3j.eth2.api.schema.AttestationData] root.
     *
     * @throws javax.ws.rs.BadRequestException Invalid request.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("aggregate_attestation")
    fun aggregateAttestation(
        @QueryParam("attestation_data_root") attestationDataRoot: String,
        @QueryParam("slot") slot: Slot
    ): BeaconResponse<Attestation>

    /**
     * Signal beacon node to prepare for a committee subnet
     * After beacon node receives this request, search using discv5 for peers related to this subnet and replace current peers with those ones if necessary If validator &#x60;is_aggregator&#x60;, beacon node must: - announce subnet topic subscription on gossipsub - aggregate attestations received on that subnet
     * @param body (optional)
     * @return void
     */
    fun prepareBeaconCommitteeSubnet(body: Array<Body6>) {
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
     * Produce an attestation data
     * Requests that the beacon node produce an AttestationData.
     * @param slot The slot for which an attestation data should be created.
     * @param committeeIndex The committee index for which an attestation data should be created.
     * @return ProduceAttestationDataResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun produceAttestationData(slot: String, committeeIndex: String): ProduceAttestationDataResponse {
        val localVariableQuery: MultiValueMap =
            mapOf("slot" to listOf("$slot"), "committee_index" to listOf("$committeeIndex"))
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
     * Produce a new block, without signature.
     * Requests a beacon node to produce a valid block, which can then be signed by a validator.
     *
     * @param slot The slot for which the block should be proposed.
     * @param randaoReveal The validator&#x27;s randao reveal value.
     * @param graffiti Arbitrary data validator wants to include in block. (optional)
     */
    @Suppress("UNCHECKED_CAST")
    fun produceBlock(slot: String, randaoReveal: String, graffiti: String): BeaconResponse<BeaconBlock> {
        val localVariableQuery: MultiValueMap =
            mapOf("randao_reveal" to listOf("$randaoReveal"), "graffiti" to listOf("$graffiti"))
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/validator/blocks/{slot}".replace("{" + "slot" + "}", "$slot"), query = localVariableQuery
        )
        val response = request<BeaconResponse<BeaconBlock>>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as BeaconResponse<BeaconBlock>
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
     * Publish multiple aggregate and proofs
     * Verifies given aggregate and proofs and publishes them on appropriate gossipsub topic.
     * @param body (optional)
     * @return void
     */
    fun publishAggregateAndProofs(body: Array<Body5>) {
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
