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

data class Validator(

    /**
     * The validator's BLS public key, uniquely identifying them.
     * _48-bytes, hex encoded with 0x prefix, case insensitive._
     */
    @JsonProperty("pubkey")
    val publicKey: BLSPublicKey,

    @JsonProperty("withdrawal_credentials")
    val withdrawalCredentials: Credentials,

    @JsonProperty("effective_balance")
    val effectiveBalance: Gwei,

    /** Was validator slashed (not longer active). */
    val slashed: Boolean,
    /** When criteria for activation were met. */
    @JsonProperty("activation_eligibility_epoch")
    val activationEligibilityEpoch: Epoch,

    /** Epoch when validator activated. 'FAR_FUTURE_EPOCH' if not activated. */
    @JsonProperty("activation_epoch")
    val activationEpoch: Epoch,

    /** Epoch when validator exited. 'FAR_FUTURE_EPOCH' if not exited. */
    @JsonProperty("exit_epoch")
    val exitEpoch: Epoch,

    /** When validator can withdraw or transfer funds. 'FAR_FUTURE_EPOCH' if not defined. */
    @JsonProperty("withdrawable_epoch")
    val withdrawableEpoch: Epoch
)
