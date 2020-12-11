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
import org.junit.jupiter.api.Test

abstract class ConfigResourceTest(private val client: BeaconNodeApi) {

    @Test
    @DisplayName("GET /fork_schedule")
    fun `get fork schedule`() {
        assertThat(client.config.forkSchedule.data).isNotEmpty()
    }

    @Test
    @DisplayName("GET /spec")
    fun `get specification parameters`() {
        assertThat(client.config.specification.data).isNotEmpty()
    }

    @Test
    @DisplayName("GET /deposit_contract")
    fun `get deposit contract address`() {
        assertThat(client.config.depositContract.data.address).isNotEmpty()
    }
}
