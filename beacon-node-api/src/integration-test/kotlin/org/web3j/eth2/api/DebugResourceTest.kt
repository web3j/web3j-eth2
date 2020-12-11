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
import assertk.assertions.isNotEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.web3j.eth2.api.schema.NamedStateId

abstract class DebugResourceTest(val client: BeaconNodeApi) {

    @Nested
    @DisplayName("/beacon")
    inner class BeaconResourceTest {

        @Nested
        @DisplayName("/states")
        inner class BeaconResourceTest {

            @Test
            @DisplayName("GET /{state_id}")
            fun `get states`() {
                assertThat(client.debug.beacon.states.findById(NamedStateId.HEAD)
                    .data.genesisValidatorsRoot).isNotEmpty()
            }
        }

        @Test
        @DisplayName("GET /heads")
        fun `get chain heads`() {
            assertThat(client.debug.beacon.heads.data).isNotEmpty()
        }
    }
}
