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
import com.google.common.base.Objects
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import tech.pegasys.teku.infrastructure.unsigned.UInt64Util

class EpochCommitteeResponse {
    @JsonProperty("index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Index of committee")
    val index: UInt64

    @JsonProperty("slot")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "The slot at which the committee has to attest.")
    val slot: UInt64

    @JsonProperty("validators")
    @ArraySchema(schema = Schema(type = "string", example = EXAMPLE_UINT64))
    val validators: List<UInt64>

    constructor(
        committeeAssignment: tech.pegasys.teku.datastructures.state.CommitteeAssignment
    ) {
        slot = committeeAssignment.getSlot()
        index = committeeAssignment.getCommitteeIndex()
        validators = UInt64Util.intToUInt64List(committeeAssignment.getCommittee())
    }

    @JsonCreator
    constructor(
        @JsonProperty("slot") slot: UInt64,
        @JsonProperty("index") index: UInt64,
        @JsonProperty("validators") validators: List<UInt64>
    ) {
        this.slot = slot
        this.index = index
        this.validators = validators
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is EpochCommitteeResponse) return false
        val that = o
        return (Objects.equal(index, that.index) &&
                Objects.equal(slot, that.slot) &&
                Objects.equal(validators, that.validators))
    }

    override fun hashCode(): Int {
        return Objects.hashCode(index, slot, validators)
    }
}
