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
package org.web3j.eth2.api.beacon.headers

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.BlockHeader
import org.web3j.eth2.api.schema.NamedBlockId
import org.web3j.eth2.api.schema.Root
import org.web3j.eth2.api.schema.Slot
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

interface HeadersResource {

    /**
     * Retrieves all block headers. By default it will fetch current head slot blocks.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): BeaconResponse<List<BlockHeader>>

    /**
     * Retrieves block headers matching given query. By default it will fetch current head slot blocks.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findBySlot(@QueryParam("slot") slot: Slot): BeaconResponse<List<BlockHeader>>

    /**
     * Retrieves block headers matching given query. By default it will fetch current head slot blocks.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByParentRoot(@QueryParam("parent_root") parentRoot: Root): BeaconResponse<List<BlockHeader>>

    /**
     * Retrieves block headers matching given query. By default it will fetch current head slot blocks.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findBySlotAndParentRoot(
        @QueryParam("slot") slot: Slot,
        @QueryParam("parent_root") parentRoot: Root
    ): BeaconResponse<List<BlockHeader>>

    /**
     * Retrieves block header for given block ID.
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
    fun findByBlockId(@PathParam("block_id") blockId: String): BeaconResponse<BlockHeader>

    /**
     * Retrieves block header for given block ID.
     *
     * @param blockId Block identifier.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}")
    fun findByBlockId(@PathParam("block_id") blockId: NamedBlockId): BeaconResponse<BlockHeader>
}
