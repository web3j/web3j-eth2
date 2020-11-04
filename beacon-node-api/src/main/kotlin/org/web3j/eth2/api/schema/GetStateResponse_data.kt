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

/**
 * The [`BeaconState`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblock) object from the Eth2.0 spec.
 * @param genesisTime
 * @param genesisValidatorsRoot
 * @param slot
 * @param fork
 * @param latestBlockHeader The [`BeaconBlockHeader`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblockheader) object from the Eth2.0 spec.
 * @param blockRoots
 * @param stateRoots
 * @param historicalRoots
 * @param eth1Data
 * @param eth1DataVotes
 * @param eth1DepositIndex
 * @param validators
 * @param balances Validator balances in gwei
 * @param randaoMixes
 * @param slashings Per-epoch sums of slashed effective balances
 * @param previousEpochAttestations
 * @param currentEpochAttestations
 * @param justificationBits Bit set for every recent justified epoch
 * @param previousJustifiedCheckpoint
 * @param currentJustifiedCheckpoint
 * @param finalizedCheckpoint
 */
data class GetStateResponseData(

    val genesisTime: String? = null,
    val genesisValidatorsRoot: String? = null,
    val slot: String? = null,
    val fork: BeaconStateFork? = null,
        /* The [`BeaconBlockHeader`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblockheader) object from the Eth2.0 spec. */
    val latestBlockHeader: AllOfGetStateResponseDataLatestBlockHeader? = null,
    val blockRoots: Array<AllOfGetStateResponseDataBlockRootsItems>? = null,
    val stateRoots: Array<AllOfGetStateResponseDataStateRootsItems>? = null,
    val historicalRoots: Array<AllOfGetStateResponseDataHistoricalRootsItems>? = null,
    val eth1Data: BeaconStateEth1Data? = null,
    val eth1DataVotes: Array<AllOfGetStateResponseDataEth1DataVotesItems>? = null,
    val eth1DepositIndex: String? = null,
    val validators: Array<AllOfGetStateResponseDataValidatorsItems>? = null,
        /* Validator balances in gwei */
    val balances: Array<AllOfGetStateResponseDataBalancesItems>? = null,
    val randaoMixes: Array<AllOfGetStateResponseDataRandaoMixesItems>? = null,
        /* Per-epoch sums of slashed effective balances */
    val slashings: Array<AllOfGetStateResponseDataSlashingsItems>? = null,
    val previousEpochAttestations: Array<AllOfGetStateResponseDataPreviousEpochAttestationsItems>? = null,
    val currentEpochAttestations: Array<AllOfGetStateResponseDataCurrentEpochAttestationsItems>? = null,
        /* Bit set for every recent justified epoch */
    val justificationBits: String? = null,
    val previousJustifiedCheckpoint: Ethv1beaconpoolattestationsDataSource? = null,
    val currentJustifiedCheckpoint: Ethv1beaconpoolattestationsDataSource? = null,
    val finalizedCheckpoint: Ethv1beaconpoolattestationsDataSource? = null
)
