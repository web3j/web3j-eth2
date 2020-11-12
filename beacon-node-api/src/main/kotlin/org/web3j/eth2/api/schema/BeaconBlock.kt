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
package org.web3j.eth2.api.schema

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * The [`BeaconBlock`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblock)
 * object from the Eth2.0 spec.
 */
data class BeaconBlock(
    /**
     * The slot to which this block corresponds.
     */
    val slot: Slot,

    /**
     * Index of validator in validator registry.
     */
    @JsonProperty("proposer_index")
    val proposerIndex: ValidatorIndex,

    /**
     * The signing merkle root of the parent [BeaconBlock].
     */
    @JsonProperty("parent_root")
    val parentRoot: Root,

    /**
     * The tree hash merkle root of the [BeaconState] for the [BeaconBlock].
     */
    @JsonProperty("state_root")
    val stateRoot: Root,

    val body: BeaconBlockBody
)
