package org.web3j.eth2.client

import org.web3j.eth2.client.apis.*
import javax.ws.rs.Path

@Path("/eth/v1")
interface BeaconNodeApi {

    /**
     * Set of endpoints to query beacon chain.
     */
    @get:Path("beacon")
    val beacon: BeaconApi

    /**
     * Endpoints to query chain configuration, specification, and fork schedules.
     */
    @get:Path("config")
    val config: ConfigApi

    /**
     * Set of endpoints to debug chain and shouldn't be exposed publicly.
     */
    @get:Path("debug")
    val debug: DebugApi

    /**
     * Set of endpoints to for event subscription.
     */
    @get:Path("events")
    val events: EventsApi

    /**
     * Endpoints to query node related information.
     */
    @get:Path("node")
    val node: NodeApi

    /**
     * Endpoints intended for validator clients.
     */
    @get:Path("validator")
    val validator: ValidatorApi

    companion object {
        fun of(service: ClientService, token: String? = null) =
                ClientFactory.create(service, token)
    }
}
