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
package org.web3j.eth2.api.beacon.pool

import org.web3j.eth2.api.schema.AttesterSlashing
import org.web3j.eth2.api.schema.BeaconResponse
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

interface AttesterSlashingsResource {

    /**
     * Get [AttesterSlashing]s from operations pool.
     *
     * Retrieves attester slashings known by the node but not necessarily incorporated into any block.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): BeaconResponse<List<AttesterSlashing>>

    /**
     * Submits [AttesterSlashing] object to node's pool
     * and if passes validation node MUST broadcast it to network.
     *
     * @throws javax.ws.rs.BadRequestException Invalid attester slashing.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun submit(body: AttesterSlashing)
}
