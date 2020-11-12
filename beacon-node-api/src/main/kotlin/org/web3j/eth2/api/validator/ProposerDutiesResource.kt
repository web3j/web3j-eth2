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
package org.web3j.eth2.api.validator

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.ProposerDuty
import javax.ws.rs.GET

interface ProposerDutiesResource {
    /**
     * Request beacon node to provide all validators that are scheduled to propose a block in the given epoch.
     *
     * @throws javax.ws.rs.BadRequestException Invalid epoch.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @GET
    fun findAll(): BeaconResponse<List<ProposerDuty>>
}
