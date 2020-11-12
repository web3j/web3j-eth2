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
 * The [`BeaconState`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblock)
 * object from the Eth2.0 spec.
 */
data class BeaconState(
    @JsonProperty("genesis_time")
    val genesisTime: String,

    @JsonProperty("genesis_validators_root")
    val genesisValidatorsRoot: Root,

    val slot: Slot,
    val fork: Fork,

    @JsonProperty("latest_block_header")
    val latestBlockHeader: BeaconBlockHeader,

    @JsonProperty("block_roots")
    val blockRoots: List<Root>,

    @JsonProperty("state_roots")
    val stateRoots: List<Root>,

    @JsonProperty("historical_roots")
    val historicalRoots: List<Root>,

    @JsonProperty("eth1_data")
    val eth1Data: Eth1Data,

    @JsonProperty("eth1_data_votes")
    val eth1DataVotes: List<Eth1Data>,

    @JsonProperty("eth1_deposit_index")
    val eth1DepositIndex: String,

    val validators: List<Validator>,

    /** Validator balances in gwei. */
    val balances: List<Gwei>,

    @JsonProperty("randao_mixes")
    val randaoMixes: List<String>,

    /** Per-epoch sums of slashed effective balances. */
    val slashings: List<Gwei>,

    @JsonProperty("previous_epoch_attestations")
    val previousEpochAttestations: List<PendingAttestation>,

    @JsonProperty("current_epoch_attestations")
    val currentEpochAttestations: List<PendingAttestation>,

    /** Bit set for every recent justified epoch. */
    @JsonProperty("justification_bits")
    val justificationBits: String,

    @JsonProperty("previous_justified_checkpoint")
    val previousJustifiedCheckpoint: Checkpoint,

    @JsonProperty("current_justified_checkpoint")
    val currentJustifiedCheckpoint: Checkpoint,

    @JsonProperty("finalized_checkpoint")
    val finalizedCheckpoint: Checkpoint
)
