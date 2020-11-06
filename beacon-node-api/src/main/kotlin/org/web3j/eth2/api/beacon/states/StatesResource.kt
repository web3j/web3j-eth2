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
package org.web3j.eth2.api.beacon.states

import org.web3j.eth2.api.schema.NamedStateId
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface StatesResource {

    /**
     * State resource locator for a given identifier.
     *
     * @param stateId State identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `justified`, `<slot>`, `<hex encoded stateRoot with 0x prefix>`.
     */
    @Path("{state_id}")
    fun withId(@PathParam("state_id") stateId: String): StateResource

    /**
     * State resource locator for a given identifier.
     *
     * @param stateId State identifier.
     */
    @Path("{state_id}")
    fun withId(@PathParam("state_id") stateId: NamedStateId): StateResource = withId(stateId.toString())
}
