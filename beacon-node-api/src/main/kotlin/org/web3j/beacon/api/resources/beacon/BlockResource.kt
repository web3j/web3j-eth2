package org.web3j.beacon.api.resources.beacon

import org.web3j.beacon.api.schema.GetBlockAttestationsResponse
import org.web3j.beacon.api.schema.GetBlockResponse
import org.web3j.beacon.api.schema.GetBlockRootResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface BlockResource {

    /**
     * Retrieves block details for given block id.
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
    fun get(@PathParam("block_id") blockId: String): GetBlockResponse

    /**
     * Retrieves attestation included in requested block.
     *

     *
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
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}/root")
    fun getBlockRoot(blockId: String): GetBlockRootResponse
}