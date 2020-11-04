package org.web3j.eth2.api.beacon.blocks

import org.web3j.eth2.api.schema.GetBlockAttestationsResponse
import java.util.function.Supplier
import javax.ws.rs.GET

interface AttestationsResource : Supplier<GetBlockAttestationsResponse> {

    /**
     * Retrieves attestation included in requested block.
     *
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    override fun get(): GetBlockAttestationsResponse
}
