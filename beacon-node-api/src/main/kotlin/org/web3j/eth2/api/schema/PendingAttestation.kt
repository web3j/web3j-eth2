/*
 * Copyright 2020 ConsenSys AG.
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

data class PendingAttestation(
    @JsonProperty("aggregation_bits")
    val aggregationBits: String, // Bitlist
    
    val data: AttestationData,

    @JsonProperty("inclusion_delay")
    val inclusionDelay: String, // UInt64

    @JsonProperty("proposer_index")
    val proposerIndex: String // UInt64
)