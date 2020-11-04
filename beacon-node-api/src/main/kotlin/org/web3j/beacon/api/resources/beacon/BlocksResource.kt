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

import org.web3j.beacon.api.schema.Body
import org.web3j.beacon.api.schema.GetBlockAttestationsResponse
import org.web3j.beacon.api.schema.GetBlockResponse
import org.web3j.beacon.api.schema.GetBlockRootResponse
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface BlocksResource {

    /**
     * Publish a signed block.
     *
     * Instructs the beacon node to broadcast a newly signed beacon block to the beacon network,
     * to be included in the beacon chain. The beacon node is not required to validate the signed
     * &#x60;BeaconBlock&#x60;, and a successful response (20X) only indicates that the broadcast has been successful.
     * The beacon node is expected to integrate the new block into its state, and therefore validate the block
     * internally, however blocks which fail the validation are still broadcast but a different status code
     * is returned (202).
     *
     * @param body The [org.web3j.beacon.api.schema.SignedBeaconBlock] object composed of
     * [org.web3j.beacon.api.schema.BeaconBlock] object (produced by beacon node) and validator signature.
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The [org.web3j.beacon.api.schema.SignedBeaconBlock] object is invalid.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @POST
    fun publish(body: Body)

    /**
     * Retrieves block details for given block id.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     * @return GetBlockResponse
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}")
    fun findById(@PathParam("block_id") blockId: String): GetBlockResponse

    /**
     * Retrieves attestation included in requested block.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     * @return GetBlockAttestationsResponse
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}/attestations")
    fun findAttestations(@PathParam("block_id") blockId: String): GetBlockAttestationsResponse

    /**
     * Retrieves hashTreeRoot of [org.web3j.beacon.api.schema.BeaconBlock]/
     * [org.web3j.beacon.api.schema.BeaconBlockHeader].
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     * @return GetBlockRootResponse
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}/root")
    fun getBlockRoot(blockId: String): GetBlockRootResponse
}
