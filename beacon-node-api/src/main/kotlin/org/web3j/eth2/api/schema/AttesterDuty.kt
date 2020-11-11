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

data class AttesterDuty(
    /**
     * The validator's BLS public key, uniquely identifying them.
     */
    @JsonProperty("pubkey")
    val publicKey: BLSPublicKey,

    /**
     * Index of validator in validator registry.
     */
    @JsonProperty("validator_index")
    val validatorIndex: ValidatorIndex,

    /**
     * The committee index.
     */
    @JsonProperty("committee_index")
    val committeeIndex: CommitteeIndex,

    /**
     * Number of validators in committee.
     */
    @JsonProperty("committee_length")
    val committeeLength: String,

    /**
     * Number of committees at the provided slot.
     */
    @JsonProperty("committees_at_slot")
    val committeesAtSlot: String,

    /**
     * Index of validator in committee.
     */
    @JsonProperty("validator_committee_index")
    val validatorCommitteeIndex: String,

    /**
     * The slot at which the validator must attest.
     */
    val slot: Slot
)
