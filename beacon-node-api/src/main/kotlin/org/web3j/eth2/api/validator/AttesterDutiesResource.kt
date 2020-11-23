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

import org.web3j.eth2.api.schema.AttesterDuty
import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.ValidatorIndex
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.core.MediaType

interface AttesterDutiesResource {
    /**
     * Requests the beacon node to provide a set of attestation duties, which should be performed by validators,
     * for a particular epoch. Duties should only need to be checked once per epoch, however a chain reorganization
     * (of > `MIN_SEED_LOOKAHEAD` epochs) could occur, resulting in a change of duties. For full safety,
     * you should monitor chain reorganizations events.
     *
     * @param validatorIndices An array of the validator indices for which to obtain the duties.
     *
     * @throws javax.ws.rs.BadRequestException Invalid epoch or index.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     * @throws javax.ws.rs.ServiceUnavailableException Beacon node is currently syncing, try again later.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    fun findByValidatorIndices(vararg validatorIndices: ValidatorIndex): BeaconResponse<List<AttesterDuty>>
}
