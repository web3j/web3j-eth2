package org.web3j.eth2.api.beacon.states

import org.web3j.eth2.api.schema.GetStateForkResponse
import java.util.function.Supplier
import javax.ws.rs.GET

interface ForkResource: Supplier<GetStateForkResponse> {

    /**
     * Get [org.web3j.eth2.api.schema.Fork] object for state.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    override fun get(): GetStateForkResponse
}
