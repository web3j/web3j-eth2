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
package org.web3j.beacon.api.request

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.infrastructure.unsigned.UInt64

class SubscribeToBeaconCommitteeRequest @JsonCreator constructor(
    @param:JsonProperty("committee_index") val committee_index: Int,
    @JsonProperty("aggregation_slot") aggregation_slot: UInt64
) {
    @Schema(type = "string", format = "uint64")
    val aggregation_slot: UInt64

    init {
        this.aggregation_slot = aggregation_slot
    }
}
