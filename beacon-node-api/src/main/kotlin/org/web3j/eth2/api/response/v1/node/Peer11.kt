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
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import java.util.Objects

@JsonInclude(JsonInclude.Include.NON_NULL)
class Peer @JsonCreator constructor(
    @field:Schema(
        type = "string",
        description = "Cryptographic hash of a peer’s public key. " +
                "'[Read more](https://docs.libp2p.io/concepts/peer-id/)",
        example = "QmYyQSo1c1Ym7orWxLYvCrM2EmxFTANf8wXmmE7DWjhx5N"
    ) @field:JsonProperty("peer_id") @param:JsonProperty("peer_id") val peerId: String,
    @field:Schema(
        type = "string",
        nullable = true,
        description = "Ethereum node record. Not currently populated. " +
                "[Read more](https://eips.ethereum.org/EIPS/eip-778)",
        example = "example: enr:-IS4QHCYrYZbAKWCBRlAy5zzaDZXJBGkcnh4MHcBFZntXNFrdvJjX04jRzjzCBOonrk" +
                "Tfj499SZuOh8R33Ls8RRcy5wBgmlkgnY0gmlwhH8AAAGJc2VjcDI1NmsxoQPKY0yuDUmstAHYp" +
                "Ma2_oxVtw0RW_QAdpzBQA8yWM0xOIN1ZHCCdl8"
    ) @field:JsonProperty("enr") @param:JsonProperty("enr") val enr: String,
    @field:Schema(
        type = "string",
        example = "/ip4/7.7.7.7/tcp/4242/p2p/QmYyQSo1c1Ym7orWxLYvCrM2EmxFTANf8wXmmE7DWjhx5N",
        description = "[Read more](https://docs.libp2p.io/reference/glossary/#multiaddr)"
    ) @field:JsonProperty("address") @param:JsonProperty("address") val address: String,
    @field:JsonProperty("state") @param:JsonProperty("state") val state: State,
    @field:JsonProperty("direction") @param:JsonProperty("direction") val direction: Direction
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val peer = o as Peer
        return (peerId == peer.peerId &&
                enr == peer.enr &&
                address == peer.address &&
                state == peer.state && direction == peer.direction)
    }

    override fun hashCode(): Int {
        return Objects.hash(peerId, enr, address, state, direction)
    }
}
