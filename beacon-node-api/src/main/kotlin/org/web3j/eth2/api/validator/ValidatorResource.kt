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
import org.web3j.eth2.api.schema.AttestationData
import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.CommitteeIndex
import org.web3j.eth2.api.schema.CommitteeSubnetSubscription
import org.web3j.eth2.api.schema.Root
import org.web3j.eth2.api.schema.SignedAggregateAndProof
import org.web3j.eth2.api.schema.Slot
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

/**
 * Endpoints intended for validator clients.
 */
interface ValidatorResource {

    /**
     * Access the `duties` subresource.
     */
    @get:Path("duties")
    val duties: DutiesResource

    @get:Path("blocks")
    val blocks: BlocksResource

    /**
     * Aggregates all attestations matching given attestation data root and slot.
     *
     * @param attestationDataRoot HashTreeRoot of [AttestationData] that validator wants aggregated.
     * @return Returns aggregated [Attestation] object with same [AttestationData] root.
     *
     * @throws javax.ws.rs.BadRequestException Invalid request.
     * @throws javax.ws.rs.ForbiddenException Beacon node was not assigned to aggregate on that subnet.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("aggregate_attestation")
    fun getAggregatedAttestation(
        @QueryParam("attestation_data_root") attestationDataRoot: Root,
        @QueryParam("slot") slot: Slot
    ): BeaconResponse<Attestation>

    /**
     * Signal beacon node to prepare for a committee subnet.
     * After beacon node receives this request, search using discv5 for peers related to this subnet and replace
     * current peers with those ones if necessary If validator `is_aggregator`, beacon node must:
     *
     *  - Announce subnet topic subscription on gossipsub.
     *  - Aggregate attestations received on that subnet.
     *
     * Successful response: slot signature is valid and beacon node has prepared the attestation subnet.
     * Note that, we cannot be certain Beacon node will find peers for that subnet for various reasons.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("beacon_committee_subscriptions")
    fun subscribe(vararg body: CommitteeSubnetSubscription)

    /**
     * Requests that the beacon node produce an [AttestationData].
     *
     * @param slot The slot for which an attestation data should be created.
     * @param committeeIndex The committee index for which an attestation data should be created.
     *
     * @throws javax.ws.rs.BadRequestException Invalid request syntax.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @GET
    @Path("attestation_data")
    fun produceAttestationData(
        @QueryParam("slot") slot: Slot,
        @QueryParam("committee_index") committeeIndex: CommitteeIndex
    ): BeaconResponse<AttestationData>

    /**
     * Publish multiple aggregate and proofs.
     *
     * Verifies given aggregate and proofs and publishes them on appropriate gossipsub topic.
     *
     * @throws javax.ws.rs.BadRequestException Invalid request syntax.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @POST
    @Path("aggregate_and_proofs")
    @Consumes(MediaType.APPLICATION_JSON)
    fun publishAggregateAndProofs(vararg body: SignedAggregateAndProof)
}
