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
 * Based on eth2 [Metadata object](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/p2p-interface.md#metadata)
 */
data class MetaData(
    /**
     * Uint64 starting at 0 used to version the node's metadata.
     * If any other field in the local MetaData changes, the node MUST increment seq_number by 1.
     */
    @JsonProperty("seq_number")
    val sequenceNumber: Uint64,
    /**
     * Bitvector representing the node's persistent attestation subnet subscriptions.
     *
     * Pattern: `^0x[a-fA-F0-9]{2,}$`
     */
    @JsonProperty("attnets")
    val attestationSubnetSubscriptions: String
)
