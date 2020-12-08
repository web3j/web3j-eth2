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
package org.web3j.eth2.api.beacon.blocks

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.NamedBlockId
import org.web3j.eth2.api.schema.SignedBeaconBlock
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.MediaType

interface BlocksResource {

    /**
     * Retrieves block details for given block ID.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}")
    fun findById(@PathParam("block_id") blockId: String): BeaconResponse<SignedBeaconBlock>

    /**
     * Retrieves block details for given block ID.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}")
    fun findById(@PathParam("block_id") blockId: NamedBlockId): BeaconResponse<SignedBeaconBlock>

    /**
     * Block resource locator for a given identifier.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @Path("{block_id}")
    fun withId(@PathParam("block_id") blockId: String): BlockResource

    /**
     * Block resource locator for a given identifier.
     *
     * @param blockId Block identifier.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @Path("{block_id}")
    fun withId(@PathParam("block_id") blockId: NamedBlockId): BlockResource

    /**
     * Publish a signed block.
     *
     * Instructs the beacon node to broadcast a newly signed beacon block to the beacon network,
     * to be included in the beacon chain. The beacon node is not required to validate the signed
     * [org.web3j.eth2.api.schema.BeaconBlock], and a successful response (`20X`) only indicates that
     * the broadcast has been successful.
     *
     * The beacon node is expected to integrate the new block into its state, and therefore validate the block
     * internally, however blocks which fail the validation are still broadcast but a different status code
     * is returned (`202`).
     *
     * @param body The [org.web3j.eth2.api.schema.SignedBeaconBlock] object composed of
     * [org.web3j.eth2.api.schema.BeaconBlock] object (produced by beacon node) and validator signature.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The [org.web3j.eth2.api.schema.SignedBeaconBlock] object is invalid.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun publish(body: SignedBeaconBlock)
}
