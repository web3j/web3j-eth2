package org.web3j.eth2.api.beacon.states

import org.web3j.eth2.api.schema.GetStateValidatorResponse
import java.util.function.Supplier
import javax.ws.rs.GET

interface ValidatorResource: Supplier<GetStateValidatorResponse> {

    /**
     * Returns validator specified by state and id or public key along with status and balance.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state or validator ID.
     * @throws javax.ws.rs.NotFoundException State or validator not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    override fun get(): GetStateValidatorResponse
}
