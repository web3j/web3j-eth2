package org.web3j.eth2.api.client

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import javax.ws.rs.core.Response

@DisplayName("/eth/v1/node")
class NodeResourceTest: BeaconNodeApiTest() {

    @Test
    @DisplayName("GET /health")
    fun `node should be healthy`() {
        assertThat(client.node.health.status)
            .isEqualTo(Response.Status.PARTIAL_CONTENT.statusCode)
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