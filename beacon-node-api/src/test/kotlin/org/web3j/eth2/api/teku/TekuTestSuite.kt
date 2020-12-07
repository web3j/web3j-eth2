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
package org.web3j.eth2.api.teku

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.testcontainers.containers.ContainerState
import org.web3j.eth2.api.BeaconNodeApiTestSuite

@Tag("teku")
@DisplayName("Teku")
class TekuTestSuite : BeaconNodeApiTestSuite() {

    override val node: ContainerState by lazy {
        teku.getContainerByServiceName("teku1_1").get()
    }

    companion object {
        @JvmStatic
        private val teku = TekuContainer().apply { start() }
    }
}
