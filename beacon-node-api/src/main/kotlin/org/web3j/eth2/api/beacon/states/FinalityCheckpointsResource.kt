package org.web3j.eth2.api.beacon.states

import org.web3j.eth2.api.schema.GetStateFinalityCheckpointsResponse
import java.util.function.Supplier
import javax.ws.rs.GET

interface FinalityCheckpointsResource : Supplier<GetStateFinalityCheckpointsResponse> {

    /**
     * Get state finality checkpoints for state.
     * In case finality is not yet achieved, checkpoint should return epoch `0` and `ZERO_HASH` as root.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    override fun get(): GetStateFinalityCheckpointsResponse
}
