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
package org.web3j.beacon.api.response.v1.node

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_UINT64
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import java.util.Objects

class Syncing @JsonCreator constructor(
    @JsonProperty("head_slot") headSlot: UInt64,
    @JsonProperty("sync_distance") syncDistance: UInt64
) {
    @JsonProperty("head_slot")
    @Schema(type = "string", pattern = PATTERN_UINT64, description = "Beacon node's head slot")
    val headSlot: UInt64

    @JsonProperty("sync_distance")
    @Schema(
        type = "string",
        pattern = PATTERN_UINT64,
        description = "How many slots node needs to process to reach head. 0 if synced."
    )
    val syncDistance: UInt64
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val syncing = o as Syncing
        return (headSlot == syncing.headSlot &&
                syncDistance == syncing.syncDistance)
    }

    override fun hashCode(): Int {
        return Objects.hash(headSlot, syncDistance)
    }

    init {
        this.headSlot = headSlot
        this.syncDistance = syncDistance
    }
}
