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
package org.web3j.beacon.api

import org.web3j.beacon.api.resources.beacon.BeaconResource
import org.web3j.beacon.api.resources.ConfigResource
import org.web3j.beacon.api.resources.DebugResource
import org.web3j.beacon.api.resources.EventsResource
import org.web3j.beacon.api.resources.NodeResource
import org.web3j.beacon.api.resources.ValidatorResource
import javax.ws.rs.Path

/**
 * Ethereum 2.0 Beacon API.
 */
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
}
