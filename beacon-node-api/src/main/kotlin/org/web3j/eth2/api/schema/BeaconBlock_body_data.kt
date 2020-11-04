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
 * The [`DepositData`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#depositdata) object from the Eth2.0 spec.
 * @param pubkey The validator's BLS public key, uniquely identifying them. _48-bytes, hex encoded with 0x prefix, case insensitive._
 * @param withdrawalCredentials
 * @param amount
 * @param signature
 */
data class BeaconBlockBodyData(

        /* The validator's BLS public key, uniquely identifying them. _48-bytes, hex encoded with 0x prefix, case insensitive._ */
    val pubkey: String? = null,
    val withdrawalCredentials: AllOfBeaconBlockBodyDataWithdrawalCredentials? = null,
    val amount: AllOfBeaconBlockBodyDataAmount? = null,
    val signature: AllOfBeaconBlockBodyDataSignature? = null
)
