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
import com.google.common.base.MoreObjects
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.tuweni.bytes.Bytes32
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_BYTES32
import tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_BYTES32
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import tech.pegasys.teku.ssz.SSZTypes.Bytes4
import java.util.Objects

class GenesisData @JsonCreator constructor(
    @JsonProperty("genesis_time") genesisTime: UInt64,
    @JsonProperty("genesis_validators_root") genesisValidatorsRoot: Bytes32,
    @JsonProperty("genesis_fork_version") genesisForkVersion: Bytes4
) {
    @JsonProperty("genesis_time")
    @Schema(
        type = "string",
        example = "1590832934",
        description = "The genesis_time configured for the beacon node, which is the unix time in seconds at which the Eth2.0 chain began."
    )
    val genesisTime: UInt64

    @JsonProperty("genesis_validators_root")
    @Schema(type = "string", example = EXAMPLE_BYTES32, pattern = PATTERN_BYTES32)
    val genesisValidatorsRoot: Bytes32

    @JsonProperty("genesis_fork_version")
    @Schema(type = "string", example = "0x00000000", pattern = "^0x[a-fA-F0-9]{8}$")
    val genesisForkVersion: Bytes4
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as GenesisData
        return (genesisTime == that.genesisTime &&
                genesisValidatorsRoot == that.genesisValidatorsRoot &&
                genesisForkVersion == that.genesisForkVersion)
    }

    override fun hashCode(): Int {
        return Objects.hash(genesisTime, genesisValidatorsRoot, genesisForkVersion)
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("genesisTime", genesisTime)
            .add("genesisValidatorsRoot", genesisValidatorsRoot)
            .add("genesisForkVersion", genesisForkVersion)
            .toString()
    }

    fun getGenesisValidatorsRoot(): Bytes32 {
        return genesisValidatorsRoot
    }

    init {
        this.genesisTime = genesisTime
        this.genesisValidatorsRoot = genesisValidatorsRoot
        this.genesisForkVersion = genesisForkVersion
    }
}
