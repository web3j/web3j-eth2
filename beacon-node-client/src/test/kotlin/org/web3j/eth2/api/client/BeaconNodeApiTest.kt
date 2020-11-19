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
package org.web3j.eth2.api.client

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.web3j.eth2.api.BeaconNodeApi
import org.web3j.eth2.api.schema.Attestation
import org.web3j.eth2.api.schema.AttestationData
import org.web3j.eth2.api.schema.AttesterSlashing
import org.web3j.eth2.api.schema.BeaconBlock
import org.web3j.eth2.api.schema.BeaconBlockBody
import org.web3j.eth2.api.schema.Checkpoint
import org.web3j.eth2.api.schema.Eth1Data
import org.web3j.eth2.api.schema.IndexedAttestation
import org.web3j.eth2.api.schema.NamedBlockId
import org.web3j.eth2.api.schema.NamedStateId
import org.web3j.eth2.api.schema.SignedBeaconBlock
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
        @DisplayName("GET /genesis")
        fun `get genesis`() {
            assertThat(client.beacon.genesis.data.time).isNotEmpty()
        }

        @Nested
        @DisplayName("GET /states/{state_id}")
        inner class StatesTest {

            private val stateResource = client.beacon.states.withId(NamedStateId.HEAD)

            @Test
            @DisplayName("GET /root")
            fun `get state root`() {
                assertThat(stateResource.root.data.root).isNotEmpty()
            }

            @Test
            @DisplayName("GET /fork")
            fun `get state fork`() {
                assertThat(stateResource.fork.data.currentVersion).isNotEmpty()
            }

            @Test
            @DisplayName("GET /finality_checkpoints")
            fun `get state finality checkpoints`() {
                assertThat(stateResource.finalityCheckpoints.data.finalized.root).isNotEmpty()
            }

            @Nested
            @DisplayName("/validators")
            inner class ValidatorsTest {

                @Test
                @DisplayName("GET /")
                @Disabled("Too long")
                fun `find all state validators`() {
                    assertThat(stateResource.validators.findAll().data).isNotEmpty()
                }

                @Test
                @DisplayName("GET /{validator_id}")
                fun `find state validator by ID`() {
                    val validator = stateResource.validators.findById("0").data
                    assertThat(validator.index).isEqualTo("0")
                }

                @Test
                @DisplayName("GET /?status=pending_initialized")
                fun `find state validators by status`() {
                    val statuses = EnumSet.of(ValidatorStatus.PENDING_INITIALIZED)
                    val validators = stateResource.validators.findByStatus(statuses).data
                    assertThat(validators.first().status).isEqualTo(ValidatorStatus.PENDING_INITIALIZED)
                }

                @Test
                @DisplayName("GET /?id=0")
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
            @DisplayName("GET /")
            fun `find all headers`() {
                assertThat(client.beacon.headers.findAll().data).isNotEmpty()
            }

            @Test
            @DisplayName("GET /?slot=0")
            fun `find headers by slot`() {
                assertThat(client.beacon.headers.findBySlot("0").data).isNotEmpty()
            }

            @Test
            @DisplayName("GET /{block_id}")
            fun `find headers by block ID`() {
                assertThat(client.beacon.headers.findByBlockId(NamedBlockId.HEAD).data.root).isNotEmpty()
            }
        }

        @Nested
        @DisplayName("/blocks")
        inner class BlocksTest {

            private val headBlock = client.beacon.blocks.withId(NamedBlockId.HEAD)

            @Nested
            @DisplayName("/{block_id}")
            inner class BlockTest {

                @Test
                @DisplayName("GET /")
                fun `get block by ID`() {
                    val block = client.beacon.blocks.findById(NamedBlockId.HEAD).data
                    assertThat(block.signature).isNotEmpty()
                }

                @Test
                @DisplayName("GET /attestations")
                fun `find all attestations`() {
                    assertThat(headBlock.attestations.findAll().data).isNotEmpty()
                }

                @Test
                @DisplayName("GET /root")
                fun `find block root`() {
                    assertThat(headBlock.root.data.root).isNotEmpty()
                }
            }

            @Test
            @DisplayName("POST")
            fun `publish block`() {
                client.beacon.blocks.publish(
                    SignedBeaconBlock(
                        message = BeaconBlock(
                            slot = "0",
                            proposerIndex = "0",
                            parentRoot = "0",
                            stateRoot = "0",
                            body = BeaconBlockBody(
                                randaoReveal = "0x0",
                                eth1Data = Eth1Data(
                                    depositRoot = "0",
                                    depositCount = "0",
                                    blockHash = "0x0"
                                ),
                                graffiti = "0",
                                proposerSlashings = emptyList(),
                                attesterSlashings = emptyList(),
                                attestations = emptyList(),
                                deposits = emptyList(),
                                voluntaryExits = emptyList()
                            )
                        ),
                        signature = "0x0"
                    )
                )
            }
        }

        @Nested
        @DisplayName("/pool")
        inner class PoolTest {

            @Nested
            @DisplayName("/attestations")
            inner class AttestationsTest {

                @Test
                @DisplayName("GET /")
                fun `find all attestations`() {
                    assertThat(client.beacon.pool.attestations.findAll().data).isEmpty()
                }

                @Test
                @DisplayName("GET /?slot=0")
                fun `find attestations by slot`() {
                    assertThat(client.beacon.pool.attestations.findBySlot("0").data).isEmpty()
                }

                @Test
                @DisplayName("GET /?committee_index=0")
                fun `find attestations by committee index`() {
                    assertThat(client.beacon.pool.attestations.findByCommitteeIndex("0").data).isEmpty()
                }

                @Test
                @DisplayName("POST")
                fun `submit attestation`() {
                    client.beacon.pool.attestations.submit(
                        Attestation(
                            aggregationBits = "0x1",
                            signature = "0x0",
                            data = AttestationData(
                                slot = "0",
                                index = "0",
                                beaconBlockRoot = "0",
                                source = Checkpoint(
                                    epoch = "0",
                                    root = "0"
                                ),
                                target = Checkpoint(
                                    epoch = "0",
                                    root = "0"
                                )
                            )
                        )
                    )
                }
            }

            @Nested
            @DisplayName("/attester_slashings")
            inner class AttesterSlashingsTest {

                @Test
                @DisplayName("GET /")
                fun `find all attestater slashings`() {
                    assertThat(client.beacon.pool.attesterSlashings.findAll().data).isEmpty()
                }

                @Test
                @DisplayName("POST")
                fun `submit attester slashings`() {
                    client.beacon.pool.attesterSlashings.submit(
                        AttesterSlashing(
                            attestation1 = IndexedAttestation(
                                attestingIndices = emptyList(),
                                `data` = AttestationData(
                                    slot = "0",
                                    index = "0",
                                    beaconBlockRoot = "0",
                                    source = Checkpoint(
                                        epoch = "0",
                                        root = "0"
                                    ),
                                    target = Checkpoint(
                                        epoch = "0",
                                        root = "0"
                                    )
                                ),
                                signature = "0x0"
                            ),
                            attestation2 = IndexedAttestation(
                                attestingIndices = emptyList(),
                                `data` = AttestationData(
                                    slot = "0",
                                    index = "0",
                                    beaconBlockRoot = "0",
                                    source = Checkpoint(
                                        epoch = "0",
                                        root = "0"
                                    ),
                                    target = Checkpoint(
                                        epoch = "0",
                                        root = "0"
                                    )
                                ),
                                signature = "0x0"
                            )
                        )
                    )
                }
            }
        }
    }

    @Nested
    @DisplayName("/node")
    inner class NodeTest {

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
}
