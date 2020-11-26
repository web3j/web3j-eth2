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
package org.web3j.eth2.lighthouse

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait

class LighthouseContainer : GenericContainer<LighthouseContainer>("sigp/lighthouse:local") {
    init {
        withCommand("lighthouse beacon --network medalla --http --http-address 0.0.0.0")
        withLogConsumer { print(it.utf8String) }
        withExposedPorts(5052, 9000)
        waitingFor(
            Wait.forHttp("/lighthouse/health")
                .forStatusCode(200)
                .forPort(5052)
        )
    }
}
