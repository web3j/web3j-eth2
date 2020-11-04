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
package org.web3j.beacon.api.request.v1.validator

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.infrastructure.unsigned.UInt64

class BeaconCommitteeSubscriptionRequest @JsonCreator constructor(
    @field:Schema(
        type = "string",
        format = "uint64"
    ) @param:JsonProperty("validator_index") val validator_index: Int,
    @field:Schema(
        type = "string",
        format = "uint64"
    ) @param:JsonProperty("committee_index") val committee_index: Int,
    @JsonProperty("committees_at_slot") committees_at_slot: UInt64,
    @JsonProperty("slot") slot: UInt64,
    @JsonProperty("is_aggregator") is_aggregator: Boolean
) {
    @Schema(type = "string", format = "uint64")
    val committees_at_slot: UInt64

    @Schema(type = "string", format = "uint64")
    val slot: UInt64
    val is_aggregator: Boolean

    init {
        this.committees_at_slot = committees_at_slot
        this.slot = slot
        this.is_aggregator = is_aggregator
    }
}
