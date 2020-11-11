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
 * Eth2.0 node network identity.
 */
data class NetworkIdentity(
    @JsonProperty("peer_id")
    val peerId: PeerId,
    val enr: ENR,
    /**
     * Node's addresses on which eth2 rpc requests are served.
     */
    @JsonProperty("p2p_addresses")
    val p2pAddresses: List<Multiaddr>,
    /**
     * Node's addresses on which is listening for discv5 requests.
     */
    @JsonProperty("discovery_addresses")
    val discoveryAddresses: List<Multiaddr>,
    val metadata: MetaData
)
