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

import org.web3j.beacon.api.schema.GetStateFinalityCheckpointsResponse
import org.web3j.beacon.api.schema.GetStateForkResponse
import org.web3j.beacon.api.schema.GetStateRootResponse
import javax.ws.rs.GET
import javax.ws.rs.Path

interface StateResource {

    /**
     * Get state finality checkpoints for state.
     * In case finality is not yet achieved, checkpoint should return epoch 0 and ZERO_HASH as root.
     */
    @get:GET
    @get:Path("finality_checkpoints")
    val finalityCheckpoints: GetStateFinalityCheckpointsResponse

    /**
     * Get [org.web3j.beacon.api.schema.Fork] object for state.
     */
    @get:GET
    @get:Path("fork")
    val fork: GetStateForkResponse

    /**
     * Calculates state SSZ HashTreeRoot. If stateId is root, same value will be returned.
     */
    @get:GET
    @get:Path("root")
    val root: GetStateRootResponse

    @get:Path("validators")
    val validators: ValidatorsResource

    @get:Path("validator_balances")
    val validatorBalances: ValidatorBalancesResource

    @get:Path("committees")
    val committees: CommitteesResource
}
