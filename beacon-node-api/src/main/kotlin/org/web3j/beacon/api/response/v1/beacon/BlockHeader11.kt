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
import tech.pegasys.teku.api.schema.BLSSignature
import tech.pegasys.teku.api.schema.BeaconBlockHeader
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_BYTES32
import tech.pegasys.teku.api.schema.SchemaConstants.PATTERN_BYTES32
import tech.pegasys.teku.api.schema.SignedBeaconBlockHeader
import tech.pegasys.teku.datastructures.blocks.SignedBeaconBlock
import java.util.Objects

class BlockHeader {
    @Schema(type = "string", example = EXAMPLE_BYTES32, pattern = PATTERN_BYTES32)
    val root: Bytes32
    val canonical: Boolean
    val header: SignedBeaconBlockHeader

    @JsonCreator
    constructor(
        @JsonProperty("root") root: Bytes32,
        @JsonProperty("canonical") canonical: Boolean,
        @JsonProperty("header") header: SignedBeaconBlockHeader
    ) {
        this.root = root
        this.canonical = canonical
        this.header = header
    }

    constructor(signedBeaconBlock: SignedBeaconBlock, canonical: Boolean) {
        this.root = signedBeaconBlock.getRoot()
        this.canonical = canonical
        header = SignedBeaconBlockHeader(
            BeaconBlockHeader(
                signedBeaconBlock.getSlot(),
                signedBeaconBlock.getMessage().getProposer_index(),
                signedBeaconBlock.getParent_root(),
                signedBeaconBlock.getStateRoot(),
                signedBeaconBlock.getRoot()
            ),
            BLSSignature(signedBeaconBlock.getSignature())
        )
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as BlockHeader
        return (canonical == that.canonical && root == that.root &&
                header == that.header)
    }

    override fun hashCode(): Int {
        return Objects.hash(root, canonical, header)
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("root", root)
            .add("canonical", canonical)
            .add("header", header)
            .toString()
    }
}
