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

import org.testcontainers.junit.jupiter.Testcontainers
import org.web3j.eth2.api.BeaconNodeApi
import org.web3j.eth2.lighthouse.LighthouseContainer

@Testcontainers
abstract class BeaconNodeApiTest {
    companion object {

        const val ROOT = "0xcf8e0d4e9587369b2301d0790347320302cc0943d5a1884560367e8208d920f2"
        const val SIGNATURE = "0x1b66ac1fb663c9bc59509846d6ec05345bd908eda73e670af888da41af171505cc411d61252fb6cb3fa0017b679f8bb2305b26a285fa2737f175668d0dff91cc1b66ac1fb663c9bc59509846d6ec05345bd908eda73e670af888da41af171505"

        @JvmStatic
        private val node = LighthouseContainer().apply { start() }

        @JvmStatic
        protected val client: BeaconNodeApi by lazy {
            BeaconNodeClientFactory.build(
                BeaconNodeService("http://${node.host}:${node.firstMappedPort}")
            )
        }
    }
}
