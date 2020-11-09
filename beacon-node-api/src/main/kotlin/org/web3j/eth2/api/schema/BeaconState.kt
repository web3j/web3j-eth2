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
    val genesisTime: String? = null,
    @JsonProperty("genesis_validators_root")
    val genesisValidatorsRoot: Root? = null,
    val slot: Slot? = null,
    val fork: Fork? = null,
    @JsonProperty("latest_block_header")
    val latestBlockHeader: BeaconBlockHeader? = null,
    @JsonProperty("block_roots")
    val blockRoots: List<Root>? = null,
    @JsonProperty("state_roots")
    val stateRoots: List<Root>? = null,
    @JsonProperty("historical_roots")
    val historicalRoots: List<Root>? = null,
    @JsonProperty("eth1_data")
    val eth1Data: Eth1Data? = null,
    @JsonProperty("eth1_data_votes")
    val eth1DataVotes: List<Eth1Data>? = null,
    @JsonProperty("eth1_deposit_index")
    val eth1DepositIndex: String? = null,
    val validators: List<Validator>? = null,
    /** Validator balances in gwei */
    val balances: List<Gwei>? = null,
    @JsonProperty("randao_mixes")
    val randaoMixes: List<String>? = null,
    /** Per-epoch sums of slashed effective balances */
    val slashings: List<Gwei>? = null,
    @JsonProperty("previous_epoch_attestations")
    val previousEpochAttestations: List<PendingAttestation>? = null,
    @JsonProperty("current_epoch_attestations")
    val currentEpochAttestations: List<PendingAttestation>? = null,
    /** Bit set for every recent justified epoch */
    @JsonProperty("justification_bits")
    val justificationBits: String? = null,
    @JsonProperty("previous_justified_checkpoint")
    val previousJustifiedCheckpoint: Checkpoint? = null,
    @JsonProperty("current_justified_checkpoint")
    val currentJustifiedCheckpoint: Checkpoint? = null,
    @JsonProperty("finalized_checkpoint")
    val finalizedCheckpoint: Checkpoint? = null
)
