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
package org.web3j.beacon.api.response.v1

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.MoreObjects
import org.apache.tuweni.bytes.Bytes32
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import java.util.Objects

@JsonIgnoreProperties(ignoreUnknown = true)
class HeadEvent @JsonCreator constructor(
    @JsonProperty(value = "slot", required = true) slot: UInt64,
    @JsonProperty("block") block: Bytes32,
    @JsonProperty("state") state: Bytes32,
    @JsonProperty("epoch_transition") epochTransition: Boolean
) {
    @JsonProperty(value = "slot", required = true)
    val slot: UInt64

    @JsonProperty("block")
    val block: Bytes32

    @JsonProperty("state")
    val state: Bytes32

    @JsonProperty("epoch_transition")
    val epochTransition: Boolean
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val headEvent = o as HeadEvent
        return (epochTransition == headEvent.epochTransition && slot == headEvent.slot &&
                block == headEvent.block &&
                state == headEvent.state)
    }

    override fun hashCode(): Int {
        return Objects.hash(slot, block, state, epochTransition)
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("slot", slot)
            .add("block", block)
            .add("state", state)
            .add("epochTransition", epochTransition)
            .toString()
    }

    init {
        this.slot = slot
        this.block = block
        this.state = state
        this.epochTransition = epochTransition
    }
}
