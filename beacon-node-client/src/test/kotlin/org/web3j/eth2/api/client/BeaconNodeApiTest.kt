package org.web3j.eth2.api.client

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.web3j.eth2.api.BeaconNodeApi
import org.web3j.eth2.api.schema.NamedBlockId
import org.web3j.eth2.api.schema.NamedStateId
import org.web3j.eth2.api.schema.ValidatorStatus
import java.util.EnumSet
import javax.ws.rs.core.Response

@DisplayName("/eth/v1")
class BeaconNodeApiTest {

    private val client: BeaconNodeApi by lazy {
        BeaconClientFactory.create(
            BeaconClientService("http://localhost:5051")
        )
    }

    @Nested
    @DisplayName("/beacon")
    inner class BeaconTest {
        
        @Test
        @DisplayName("/genesis")
        fun `get genesis`() {
            assertThat(client.beacon.genesis.time).isNotEmpty()
        }

        @Nested
        @DisplayName("/states/{state_id}")
        inner class StatesTest {

            private val stateResource = client.beacon.states.withId(NamedStateId.HEAD)

            @Test
            @DisplayName("/root")
            fun `get state root`() {
                assertThat(stateResource.root.data.root).isNotEmpty()
            }

            @Test
            @DisplayName("/fork")
            fun `get state fork`() {
                assertThat(stateResource.fork.data.currentVersion).isNotEmpty()
            }

            @Test
            @DisplayName("/finality_checkpoints")
            fun `get state finality checkpoints`() {
                assertThat(stateResource.finalityCheckpoints.data.finalized.root).isNotEmpty()
            }
            
            @Nested
            @DisplayName("/validators")
            inner class ValidatorsTest {

                @Test
                @DisplayName("/")
                @Disabled("Too long")
                fun `find all state validators`() {
                    assertThat(stateResource.validators.findAll().data).isNotEmpty()
                }

                @Test
                @DisplayName("/{validator_id}")
                fun `find state validator by ID`() {
                    val validator = stateResource.validators.findById("0").data
                    assertThat(validator.index).isEqualTo("0")
                }

                @Test
                @DisplayName("?status=pending_initialized")
                fun `find state validators by status`() {
                    val statuses = EnumSet.of(ValidatorStatus.PENDING_INITIALIZED)
                    val validators = stateResource.validators.findByStatus(statuses).data
                    assertThat(validators.first().status).isEqualTo(ValidatorStatus.PENDING_INITIALIZED)
                }

                @Test
                @DisplayName("?id=0")
                fun `find state validators by IDs`() {
                    val validators = stateResource.validators.findByIds(listOf("0")).data
                    assertThat(validators.first().index).isEqualTo("0")
                }
            }
        }

        @Nested
        @DisplayName("/headers")
        inner class HeadersTest {

            @Test
            @DisplayName("/")
            fun `find all headers`() {
                assertThat(client.beacon.headers.findAll().data).is
            }
        }

        @Nested
        @DisplayName("/blocks")
        inner class BlocksTest {
            
            private val headBlock = client.beacon.blocks.withId(NamedBlockId.HEAD)
            
            @Test
            @DisplayName("/{block_id}")
            fun `get block by ID`() {
                val block = client.beacon.blocks.findById(NamedBlockId.HEAD).data
                assertThat(block.signature).isNotEmpty()
            }

            @Test
            @DisplayName("/{block_id}/attestations")
            fun `find all attestations`() {
                assertThat(headBlock.attestations.findAll().data).isNotEmpty()
            }

            @Test
            @DisplayName("/{block_id}/root")
            fun `find block root`() {
                assertThat(headBlock.root.data.root).isNotEmpty()
            }
            
            @Test
            @Disabled("TODO")
            @DisplayName("/")
            fun `publish block`() {
                TODO()
            }
        }

        @Nested
        @DisplayName("/pool")
        inner class PoolTest {

        }
    }

    @Nested
    @DisplayName("/node")
    inner class NodeTest {

        @Test
        @DisplayName("/health")
        fun `node should be healthy`() {
            assertThat(client.node.health.status)
                .isEqualTo(Response.Status.PARTIAL_CONTENT.statusCode)
        }

        @Test
        @DisplayName("identity")
        fun `get node identity`() {
            assertThat(client.node.identity.data.peerId).isNotEmpty()
        }

        @Test
        @DisplayName("version")
        fun `get node version`() {
            assertThat(client.node.identity.data.peerId).isNotEmpty()
        }

        @Test
        @DisplayName("syncing")
        fun `get node syncing`() {
            assertThat(client.node.syncing.data.syncDistance).isNotEmpty()
        }

        @Nested
        @DisplayName("/peers")
        inner class PeersTest {

            @Test
            @DisplayName("/")
            fun `find all node peers`() {
                assertThat(client.node.peers.findAll().data).isNotEmpty()
            }

            @Test
            @DisplayName("/{peer_id}")
            fun `find node peers by ID`() {
                val peer = client.node.peers.findAll().data.first()
                assertThat(client.node.peers.findById(peer.id).data.id).isEqualTo(peer.id)
            }
        }
    }

}