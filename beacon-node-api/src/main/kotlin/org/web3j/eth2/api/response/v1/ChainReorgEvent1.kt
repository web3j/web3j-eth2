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
package org.web3j.eth2.api.response.v1

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.MoreObjects
import org.apache.tuweni.bytes.Bytes32
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import java.util.Objects

@JsonIgnoreProperties(ignoreUnknown = true)
class ChainReorgEvent @JsonCreator constructor(
    @JsonProperty(value = "slot", required = true) slot: UInt64,
    @JsonProperty(value = "depth", required = true) depth: UInt64,
    @JsonProperty("old_head_block") oldHeadBlock: Bytes32,
    @JsonProperty("new_head_block") newHeadBlock: Bytes32,
    @JsonProperty("old_head_state") oldHeadState: Bytes32,
    @JsonProperty("new_head_state") newHeadState: Bytes32,
    @JsonProperty("epoch") epoch: UInt64
) {
    @JsonProperty(value = "slot", required = true)
    val slot: UInt64

    @JsonProperty(value = "depth", required = true)
    val depth: UInt64

    @JsonProperty("old_head_block")
    val oldHeadBlock: Bytes32

    @JsonProperty("new_head_block")
    val newHeadBlock: Bytes32

    @JsonProperty("old_head_state")
    val oldHeadState: Bytes32

    @JsonProperty("new_head_state")
    val newHeadState: Bytes32

    @JsonProperty("epoch")
    val epoch: UInt64
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ChainReorgEvent
        return (slot == that.slot &&
                depth == that.depth &&
                oldHeadBlock == that.oldHeadBlock &&
                newHeadBlock == that.newHeadBlock &&
                oldHeadState == that.oldHeadState &&
                newHeadState == that.newHeadState &&
                epoch == that.epoch)
    }

    override fun hashCode(): Int {
        return Objects.hash(slot, depth, oldHeadBlock, newHeadBlock, oldHeadState, newHeadState, epoch)
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("slot", slot)
            .add("depth", depth)
            .add("oldHeadBlock", oldHeadBlock)
            .add("newHeadBlock", newHeadBlock)
            .add("oldHeadState", oldHeadState)
            .add("newHeadState", newHeadState)
            .add("epoch", epoch)
            .toString()
    }

    init {
        this.slot = slot
        this.depth = depth
        this.oldHeadBlock = oldHeadBlock
        this.newHeadBlock = newHeadBlock
        this.oldHeadState = oldHeadState
        this.newHeadState = newHeadState
        this.epoch = epoch
    }
}
