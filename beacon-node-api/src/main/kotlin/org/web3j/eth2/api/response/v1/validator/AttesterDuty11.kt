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
package org.web3j.eth2.api.response.v1.validator

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.BLSPubKey
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_PUBKEY
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64
import tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_PUBKEY
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import java.util.Objects

class AttesterDuty @JsonCreator constructor(
    @JsonProperty("pubkey") pubkey: BLSPubKey,
    @JsonProperty("validator_index") validatorIndex: UInt64,
    @JsonProperty("committee_index") committeeIndex: UInt64,
    @JsonProperty("committee_length") committeeLength: UInt64,
    @JsonProperty("committees_at_slot") committeesAtSlot: UInt64,
    @JsonProperty("validator_committee_index") validatorCommitteeIndex: UInt64,
    @JsonProperty("slot") slot: UInt64
) {
    @JsonProperty("pubkey")
    @Schema(
        type = "string",
        pattern = PATTERN_PUBKEY,
        example = EXAMPLE_PUBKEY,
        description = "The validator's BLS public key, uniquely identifying them. " +
                "48-bytes, hex encoded with 0x prefix, case insensitive."
    )
    val pubkey: BLSPubKey

    @JsonProperty("validator_index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Index of validator in validator registry")
    val validatorIndex: UInt64

    @JsonProperty("committee_index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "The committee index")
    val committeeIndex: UInt64

    @JsonProperty("committee_length")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Number of validators in committee")
    val committeeLength: UInt64

    @JsonProperty("committees_at_slot")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Number of committees at the provided slot")
    val committeesAtSlot: UInt64

    @JsonProperty("validator_committee_index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Index of validator in committee")
    val validatorCommitteeIndex: UInt64

    @JsonProperty("slot")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "The slot at which the validator must attest.")
    val slot: UInt64
    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as AttesterDuty
        return (pubkey == that.pubkey &&
                validatorIndex == that.validatorIndex &&
                committeeIndex == that.committeeIndex &&
                committeeLength == that.committeeLength &&
                committeesAtSlot == that.committeesAtSlot &&
                validatorCommitteeIndex == that.validatorCommitteeIndex &&
                slot == that.slot)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            pubkey,
            validatorIndex,
            committeeIndex,
            committeeLength,
            committeesAtSlot,
            validatorCommitteeIndex,
            slot
        )
    }

    init {
        this.pubkey = pubkey
        this.validatorIndex = validatorIndex
        this.committeeIndex = committeeIndex
        this.committeeLength = committeeLength
        this.committeesAtSlot = committeesAtSlot
        this.validatorCommitteeIndex = validatorCommitteeIndex
        this.slot = slot
    }
}
