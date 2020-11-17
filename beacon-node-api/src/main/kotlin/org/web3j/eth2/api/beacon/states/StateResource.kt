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

import org.web3j.eth2.api.beacon.states.validators.ValidatorBalancesResource
import org.web3j.eth2.api.beacon.states.validators.ValidatorsResource
import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.BlockRoot
import org.web3j.eth2.api.schema.Fork
import org.web3j.eth2.api.schema.StateFinalityCheckpoint
import javax.ws.rs.GET
import javax.ws.rs.Path

interface StateResource {

    /**
     * Calculates state SSZ HashTreeRoot. If `stateId` is root, same value will be returned.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("root")
    val root: BeaconResponse<BlockRoot>

    /**
     * Get [org.web3j.eth2.api.schema.Fork] object for state.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("fork")
    val fork: BeaconResponse<Fork>

    /**
     * Get state finality checkpoints for state.
     * In case finality is not yet achieved, checkpoint should return epoch `0` and `ZERO_HASH` as root.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("finality_checkpoints")
    val finalityCheckpoints: BeaconResponse<StateFinalityCheckpoint>

    @get:Path("validators")
    val validators: ValidatorsResource

    @get:Path("validator_balances")
    val validatorBalances: ValidatorBalancesResource

    @get:Path("committees")
    val committees: CommitteesResource
}
