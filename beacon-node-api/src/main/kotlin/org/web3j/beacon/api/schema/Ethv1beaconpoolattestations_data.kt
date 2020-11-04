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
package org.web3j.beacon.api.schema

/**
 * The [`AttestationData`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#attestationdata) object from the Eth2.0 spec.
 * @param slot
 * @param index
 * @param beaconBlockRoot
 * @param source
 * @param target
 */
data class Ethv1beaconpoolattestationsData(

    val slot: String? = null,
    val index: String? = null,
    val beaconBlockRoot: AllOfethv1beaconpoolattestationsDataBeaconBlockRoot? = null,
    val source: Ethv1beaconpoolattestationsDataSource? = null,
    val target: Ethv1beaconpoolattestationsDataSource? = null
)
