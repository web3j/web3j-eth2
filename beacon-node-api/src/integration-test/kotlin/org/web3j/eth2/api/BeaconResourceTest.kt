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
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.web3j.eth2.api.BeaconNodeApiTestSuite.Companion.ROOT
import org.web3j.eth2.api.BeaconNodeApiTestSuite.Companion.SIGNATURE
import org.web3j.eth2.api.schema.Attestation
import org.web3j.eth2.api.schema.AttestationData
import org.web3j.eth2.api.schema.AttesterSlashing
import org.web3j.eth2.api.schema.BeaconBlock
import org.web3j.eth2.api.schema.BeaconBlockBody
import org.web3j.eth2.api.schema.BeaconBlockHeader
import org.web3j.eth2.api.schema.Checkpoint
import org.web3j.eth2.api.schema.Eth1Data
import org.web3j.eth2.api.schema.IndexedAttestation
import org.web3j.eth2.api.schema.NamedBlockId
import org.web3j.eth2.api.schema.NamedStateId
import org.web3j.eth2.api.schema.ProposerSlashing
import org.web3j.eth2.api.schema.SignedBeaconBlock
import org.web3j.eth2.api.schema.SignedBeaconBlockHeader
import org.web3j.eth2.api.schema.SignedVoluntaryExit
import org.web3j.eth2.api.schema.ValidatorStatus
import org.web3j.eth2.api.schema.VoluntaryExit
import java.util.EnumSet

abstract class BeaconResourceTest(val client: BeaconNodeApi) {

    @Test
    @DisplayName("GET /genesis")
    fun `get genesis`() {
        assertThat(client.beacon.genesis.data.time).isNotEmpty()
    }

    @Nested
    @DisplayName("/states/{state_id}")
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
            @DisplayName("GET /?status=active_ongoing")
            fun `find state validators by status`() {
                val statuses = EnumSet.of(ValidatorStatus.ACTIVE_ONGOING)
                val validators = stateResource.validators.findByStatus(statuses).data
                assertThat(validators.first().status).isEqualTo(ValidatorStatus.ACTIVE_ONGOING)
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
        @Disabled
        @DisplayName("POST /")
        fun `publish block`() {
            client.beacon.blocks.publish(
                SignedBeaconBlock(
                    message = BeaconBlock(
                        slot = "0",
                        proposerIndex = "0",
                        parentRoot = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda",
                        stateRoot = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda",
                        body = BeaconBlockBody(
                            randaoReveal = "0x0",
                            eth1Data = Eth1Data(
                                depositRoot = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda",
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
            @Disabled
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
            @Disabled
            @DisplayName("GET /?committee_index=0")
            fun `find attestations by committee index`() {
                assertThat(client.beacon.pool.attestations.findByCommitteeIndex("0").data).isEmpty()
            }

            @Test
            @Disabled
            @DisplayName("POST /")
            fun `submit attestation`() {
                client.beacon.pool.attestations.submit(
                    Attestation(
                        aggregationBits = "0x01",
                        signature = "0x01",
                        data = AttestationData(
                            slot = "0",
                            index = "0",
                            beaconBlockRoot = "0",
                            source = Checkpoint(
                                epoch = "0",
                                root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
                            ),
                            target = Checkpoint(
                                epoch = "0",
                                root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
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
            fun `find all attester slashings`() {
                assertThat(client.beacon.pool.attesterSlashings.findAll().data).isEmpty()
            }

            @Test
            @Disabled
            @DisplayName("POST /")
            fun `submit attester slashing`() {
                client.beacon.pool.attesterSlashings.submit(
                    AttesterSlashing(
                        attestation1 = IndexedAttestation(
                            attestingIndices = emptyList(),
                            `data` = AttestationData(
                                slot = "0",
                                index = "0",
                                beaconBlockRoot = ROOT,
                                source = Checkpoint(
                                    epoch = "0",
                                    root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
                                ),
                                target = Checkpoint(
                                    epoch = "0",
                                    root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
                                )
                            ),
                            signature = SIGNATURE
                        ),
                        attestation2 = IndexedAttestation(
                            attestingIndices = emptyList(),
                            `data` = AttestationData(
                                slot = "0",
                                index = "0",
                                beaconBlockRoot = "0x01",
                                source = Checkpoint(
                                    epoch = "0",
                                    root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
                                ),
                                target = Checkpoint(
                                    epoch = "0",
                                    root = "0x83431ec7fcf92cfc44947fc0418e831c25e1d0806590231c439830db7ad54fda"
                                )
                            ),
                            signature = "0x01"
                        )
                    )
                )
            }
        }

        @Nested
        @DisplayName("/proposer_slashings")
        inner class ProposerSlashingsTest {

            @Test
            @DisplayName("GET /")
            fun `find all proposer slashings`() {
                assertThat(client.beacon.pool.proposerSlashings.findAll().data).isEmpty()
            }

            @Test
            @Disabled
            @DisplayName("POST /")
            fun `submit proposer slashing`() {
                client.beacon.pool.proposerSlashings.submit(
                    ProposerSlashing(
                        signedHeader1 = SignedBeaconBlockHeader(
                            message = BeaconBlockHeader(
                                slot = "0",
                                proposerIndex = "0",
                                parentRoot = "0x01",
                                stateRoot = "0x01",
                                bodyRoot = "0x01"
                            ), signature = "0x01"
                        ),
                        signedHeader2 = SignedBeaconBlockHeader(
                            message = BeaconBlockHeader(
                                slot = "0",
                                proposerIndex = "0",
                                parentRoot = "0x01",
                                stateRoot = "0x01",
                                bodyRoot = "0x01"
                            ), signature = "0x01"
                        )
                    )
                )
            }
        }

        @Nested
        @DisplayName("/voluntary_exits")
        inner class VoluntaryExitsTest {

            @Test
            @DisplayName("GET /")
            fun `find all voluntary exits`() {
                assertThat(client.beacon.pool.voluntaryExits.findAll().data).isEmpty()
            }

            @Test
            @Disabled
            @DisplayName("POST /")
            fun `submit voluntary exit`() {
                client.beacon.pool.voluntaryExits.submit(
                    SignedVoluntaryExit(
                        message = VoluntaryExit(
                            epoch = "0",
                            validatorIndex = "0"
                        ),
                        signature = SIGNATURE
                    )
                )
            }
        }
    }
}
