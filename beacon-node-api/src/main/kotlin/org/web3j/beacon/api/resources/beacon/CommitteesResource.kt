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
package org.web3j.beacon.api.resources.beacon

import org.web3j.beacon.api.schema.Epoch
import org.web3j.beacon.api.schema.GetEpochCommitteesResponse
import org.web3j.beacon.api.schema.Index
import org.web3j.beacon.api.schema.Slot
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface CommitteesResource {

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     * @param index Committee index (optional)
     * @param slot (optional)
     */
    @GET
    @Path("{epoch}")
    fun findBy(
        @PathParam("epoch") epoch: Epoch?,
        index: Index? = null,
        slot: Slot? = null
    ): GetEpochCommitteesResponse
}
