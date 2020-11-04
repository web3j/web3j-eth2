package org.web3j.eth2.api.beacon.states

import org.web3j.eth2.api.schema.GetStateRootResponse
import java.util.function.Supplier
import javax.ws.rs.GET

interface StateRootResource : Supplier<GetStateRootResponse> {

    /**
     * Calculates state SSZ HashTreeRoot. If stateId is root, same value will be returned.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    override fun get(): GetStateRootResponse
}
