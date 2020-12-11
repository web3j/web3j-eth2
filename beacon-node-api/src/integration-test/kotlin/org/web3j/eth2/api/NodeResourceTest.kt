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
package org.web3j.eth2.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response

abstract class NodeResourceTest(val client: BeaconNodeApi) {

    @Test
    @DisplayName("GET /health")
    fun `node should be healthy`() {
        assertThat(client.node.health.status)
            .isEqualTo(Response.Status.OK.statusCode)
    }

    @Test
    @DisplayName("GET /identity")
    fun `get node identity`() {
        assertThat(client.node.identity.data.peerId).isNotEmpty()
    }

    @Test
    @DisplayName("GET /version")
    fun `get node version`() {
        assertThat(client.node.identity.data.peerId).isNotEmpty()
    }

    @Test
    @DisplayName("GET /syncing")
    fun `get node syncing`() {
        assertThat(client.node.syncing.data.syncDistance).isNotEmpty()
    }

    @Nested
    @DisplayName("/peers")
    inner class PeersTest {

        @Test
        @DisplayName("GET /")
        fun `find all node peers`() {
            assertThat(client.node.peers.findAll().data).isNotEmpty()
        }

        @Test
        @DisplayName("GET /{peer_id}")
        fun `find node peers by ID`() {
            val peer = client.node.peers.findAll().data.first()
            assertThat(client.node.peers.findById(peer.id).data.id).isEqualTo(peer.id)
        }
    }
}
