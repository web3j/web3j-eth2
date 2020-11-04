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
 *
 * @param peerId Cryptographic hash of a peer’s public key. [Read more](https://docs.libp2p.io/concepts/peer-id/)
 * @param enr Ethereum node record. [Read more](https://eips.ethereum.org/EIPS/eip-778)
 * @param p2pAddresses
 * @param discoveryAddresses
 * @param metadata
 */
data class NetworkIdentity(

        /* Cryptographic hash of a peer’s public key. [Read more](https://docs.libp2p.io/concepts/peer-id/) */
    val peerId: String? = null,
        /* Ethereum node record. [Read more](https://eips.ethereum.org/EIPS/eip-778) */
    val enr: String? = null,
    val p2pAddresses: Array<AllOfNetworkIdentityP2pAddressesItems>? = null,
    val discoveryAddresses: Array<AllOfNetworkIdentityDiscoveryAddressesItems>? = null,
    val metadata: NetworkIdentityMetadata? = null
)
