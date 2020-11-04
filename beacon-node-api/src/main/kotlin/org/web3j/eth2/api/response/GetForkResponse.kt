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
package org.web3j.eth2.api.response

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.tuweni.bytes.Bytes32
import tech.pegasys.teku.api.schema.SchemaConstants.DESCRIPTION_BYTES32
import tech.pegasys.teku.api.schema.SchemaConstants.DESCRIPTION_BYTES4
import tech.pegasys.teku.datastructures.state.Fork
import tech.pegasys.teku.datastructures.state.ForkInfo
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import tech.pegasys.teku.ssz.SSZTypes.Bytes4

class GetForkResponse {
    @Schema(type = "string", format = "byte", description = DESCRIPTION_BYTES4)
    var previous_version: Bytes4? = null

    @Schema(type = "string", format = "byte", description = DESCRIPTION_BYTES4)
    var current_version: Bytes4? = null

    @Schema(type = "string", format = "uint64")
    var epoch: UInt64? = null

    @Schema(type = "string", format = "byte", description = DESCRIPTION_BYTES32)
    val genesis_validators_root: Bytes32

    @JsonCreator
    constructor(
        @JsonProperty("previous_version") previous_version: Bytes4?,
        @JsonProperty("current_version") current_version: Bytes4?,
        @JsonProperty("epoch") epoch: UInt64?,
        @JsonProperty("genesis_validators_root") genesis_validators_root: Bytes32
    ) {
        this.previous_version = previous_version
        this.current_version = current_version
        this.epoch = epoch
        this.genesis_validators_root = genesis_validators_root
    }

    constructor(forkInfo: ForkInfo) {
        val fork: Fork = forkInfo.getFork()
        if (fork != null) {
            previous_version = fork.getPrevious_version()
            current_version = fork.getCurrent_version()
            epoch = fork.getEpoch()
        }
        genesis_validators_root = forkInfo.getGenesisValidatorsRoot()
    }

    fun asInternalForkInfo(): tech.pegasys.teku.datastructures.state.ForkInfo {
        return ForkInfo(
            Fork(previous_version, current_version, epoch), genesis_validators_root
        )
    }
}
