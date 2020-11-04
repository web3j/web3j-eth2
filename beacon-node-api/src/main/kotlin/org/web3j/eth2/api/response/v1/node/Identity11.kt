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
package org.web3j.eth2.api.response.v1.node

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.Metadata
import java.util.Objects

class Identity @JsonCreator constructor(
    @field:Schema(
        description = "Cryptographic hash of a peer’s public key. " +
                "[Read more](https://docs.libp2p.io/concepts/peer-id/)",
        example = "QmYyQSo1c1Ym7orWxLYvCrM2EmxFTANf8wXmmE7DWjhx5N"
    ) @field:JsonProperty("peer_id") @param:JsonProperty("peer_id") val peerId: String,
    @field:Schema(
        description = "Ethereum node record. " + "[Read more](https://eips.ethereum.org/EIPS/eip-778)",
        example = "enr:-IS4QHCYrYZbAKWCBRlAy5zzaDZXJBGkcnh4MHcBFZntXNFrdvJjX04jRzjzCBOonrkTfj499S" +
                "ZuOh8R33Ls8RRcy5wBgmlkgnY0gmlwhH8AAAGJc2VjcDI1NmsxoQPKY0yuDUmstAHYpMa2_oxVtw0RW_QAdpzBQA8yWM0xOIN1ZHCCdl8"
    ) @field:JsonProperty("enr") @param:JsonProperty("enr") val enr: String,
    @field:ArraySchema(
        arraySchema = Schema(
            type = "string",
            description = "Node's addresses on which eth2 rpc requests are served. " +
                    "[Read more](https://docs.libp2p.io/reference/glossary/#multiaddr)",
            example = "/ip4/7.7.7.7/tcp/4242/p2p/QmYyQSo1c1Ym7orWxLYvCrM2EmxFTANf8wXmmE7DWjhx5N"
        )
    ) @field:JsonProperty("p2p_addresses") @param:JsonProperty("p2p_addresses") val p2pAddresses: List<String>,
    @field:ArraySchema(
        arraySchema = Schema(
            type = "string",
            description = "Node's addresses on which is listening for discv5 requests. " +
                    "[Read more](https://docs.libp2p.io/reference/glossary/#multiaddr)",
            example = "/ip4/7.7.7.7/tcp/4242/p2p/QmYyQSo1c1Ym7orWxLYvCrM2EmxFTANf8wXmmE7DWjhx5N"
        )
    ) @field:JsonProperty("discovery_addresses") @param:JsonProperty("discovery_addresses") val discoveryAddresses: List<String>,
    @field:JsonProperty("metadata") @param:JsonProperty("metadata") val metadata: Metadata
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val identity = o as Identity
        return (peerId == identity.peerId &&
                enr == identity.enr &&
                p2pAddresses == identity.p2pAddresses &&
                discoveryAddresses == identity.discoveryAddresses &&
                metadata == identity.metadata)
    }

    override fun hashCode(): Int {
        return Objects.hash(peerId, enr, p2pAddresses, discoveryAddresses, metadata)
    }
}
