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

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.testcontainers.containers.ContainerState
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
abstract class BeaconNodeApiTestSuite {

    @Nested
    @DisplayName("/eth/v1/beacon")
    inner class BeaconResource : BeaconResourceTest(client)

    @Nested
    @DisplayName("/eth/v1/config")
    inner class ConfigResource : ConfigResourceTest(client)

    @Nested
    @DisplayName("/eth/v1/debug")
    inner class DebugResource : DebugResourceTest(client)

    @Nested
    @DisplayName("/eth/v1/events")
    inner class EventsResource : EventsResourceTest(client)

    @Nested
    @DisplayName("/eth/v1/node")
    inner class NodeResource : NodeResourceTest(client)

    @Nested
    @DisplayName("/eth/v1/validator")
    inner class ValidatorResource : ValidatorResourceTest(client)

    protected abstract val node: ContainerState

    protected val client: BeaconNodeApi by lazy {
        BeaconNodeClientFactory.build(
            BeaconNodeService("http://${node.host}:${node.firstMappedPort}")
        )
    }

    companion object {
        const val ROOT = "0xcf8e0d4e9587369b2301d0790347320302cc0943d5a1884560367e8208d920f2"
        const val SIGNATURE =
            "0x1b66ac1fb663c9bc59509846d6ec05345bd908eda73e670af888da41af171505cc411d61252fb6cb3fa0017b679f8bb2305b26a285fa2737f175668d0dff91cc1b66ac1fb663c9bc59509846d6ec05345bd908eda73e670af888da41af171505"
    }
}
