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

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.BLSPubKey
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_PUBKEY
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64
import tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_PUBKEY
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import java.util.Objects

class ProposerDuty(
    @JsonProperty("pubkey") pubkey: BLSPubKey,
    @JsonProperty("validator_index") validatorIndex: Int,
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

    @JsonProperty("slot")
    @Schema(
        type = "string",
        example = EXAMPLE_UINT64,
        description = "The slot at which the validator must propose block."
    )
    val slot: UInt64
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ProposerDuty
        return (pubkey == that.pubkey &&
                validatorIndex == that.validatorIndex &&
                slot == that.slot)
    }

    override fun hashCode(): Int {
        return Objects.hash(pubkey, validatorIndex, slot)
    }

    init {
        this.pubkey = pubkey
        this.validatorIndex = UInt64.valueOf(validatorIndex)
        this.slot = slot
    }
}
