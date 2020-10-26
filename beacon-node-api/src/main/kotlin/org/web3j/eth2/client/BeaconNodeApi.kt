package org.web3j.eth2.client

import org.web3j.eth2.client.resources.BeaconResource
import org.web3j.eth2.client.resources.ConfigResource
import org.web3j.eth2.client.resources.DebugResource
import org.web3j.eth2.client.resources.EventsResource
import org.web3j.eth2.client.resources.NodeResource
import org.web3j.eth2.client.resources.ValidatorResource
import javax.ws.rs.Path

@Path("/eth/v1")
interface BeaconNodeApi {

    /**
     * Set of endpoints to query beacon chain.
     */
    @get:Path("beacon")
    val beacon: BeaconResource

    /**
     * Endpoints to query chain configuration, specification, and fork schedules.
     */
    @get:Path("config")
    val config: ConfigResource

    /**
     * Set of endpoints to debug chain and shouldn't be exposed publicly.
     */
    @get:Path("debug")
    val debug: DebugResource

    /**
     * Set of endpoints to for event subscription.
     */
    @get:Path("events")
    val events: EventsResource

    /**
     * Endpoints to query node related information.
     */
    @get:Path("node")
    val node: NodeResource

    /**
     * Endpoints intended for validator clients.
     */
    @get:Path("validator")
    val validator: ValidatorResource

    companion object {
        fun of(service: ClientService, token: String? = null) = ClientFactory.create(service, token)
    }
}
