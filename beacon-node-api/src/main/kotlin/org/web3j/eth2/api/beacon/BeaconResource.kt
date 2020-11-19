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
package org.web3j.eth2.api.beacon

import org.web3j.eth2.api.beacon.blocks.BlocksResource
import org.web3j.eth2.api.beacon.headers.HeadersResource
import org.web3j.eth2.api.beacon.pool.PoolResource
import org.web3j.eth2.api.beacon.states.StatesResource
import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.Genesis
import javax.ws.rs.GET
import javax.ws.rs.Path

/**
 * Set of endpoints to query beacon chain.
 */
interface BeaconResource {

    @get:Path("states")
    val states: StatesResource

    @get:Path("headers")
    val headers: HeadersResource

    @get:Path("blocks")
    val blocks: BlocksResource

    @get:Path("pool")
    val pool: PoolResource

    /**
     * Retrieve details of the chain's genesis which can be used to identify chain.
     *
     * @throws javax.ws.rs.NotFoundException Chain genesis info is not yet known.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("genesis")
    val genesis: BeaconResponse<Genesis>
}
