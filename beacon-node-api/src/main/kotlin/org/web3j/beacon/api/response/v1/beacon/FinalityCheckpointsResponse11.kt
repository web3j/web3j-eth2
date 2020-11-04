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
package org.web3j.beacon.api.response.v1.beacon

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import tech.pegasys.teku.api.schema.Checkpoint
import tech.pegasys.teku.datastructures.state.BeaconState

class FinalityCheckpointsResponse @JsonCreator constructor(
    @JsonProperty("previous_justified") previous_justified: Checkpoint,
    @JsonProperty("current_justified") current_justified: Checkpoint,
    @JsonProperty("finalized") finalized: Checkpoint
) {
    @JsonProperty("previous_justified")
    val previous_justified: Checkpoint

    @JsonProperty("current_justified")
    val current_justified: Checkpoint

    @JsonProperty("finalized")
    val finalized: Checkpoint

    companion object {
        fun fromState(state: BeaconState): FinalityCheckpointsResponse {
            return FinalityCheckpointsResponse(
                Checkpoint(state.getPrevious_justified_checkpoint()),
                Checkpoint(state.getCurrent_justified_checkpoint()),
                Checkpoint(state.getFinalized_checkpoint())
            )
        }
    }

    init {
        this.previous_justified = previous_justified
        this.current_justified = current_justified
        this.finalized = finalized
    }
}
