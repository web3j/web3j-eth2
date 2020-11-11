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
package org.web3j.eth2.api.debug

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.DebugChainHead
import javax.ws.rs.GET
import javax.ws.rs.Path

interface BeaconResource {

    @get:Path("states")
    val states: StatesResource

    /**
     * Get fork choice leaves.
     * Retrieves all possible chain heads (leaves of fork choice tree).
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("heads")
    val heads: BeaconResponse<List<DebugChainHead>>
}
