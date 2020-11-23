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
package org.web3j.eth2.teku

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import java.time.Duration

class TekuContainer : GenericContainer<TekuContainer>("pegasyseng/teku:latest") {
    init {
        withCommand("--rest-api-enabled=true --eth1-endpoint=http://localhost:8545")
        withLogConsumer { print(it.utf8String) }
        withExposedPorts(5051, 8545)
        waitingFor(
            Wait.forHttp("/eth/v1/node/health")
                .forStatusCode(206).forPort(5051)
                .withStartupTimeout(Duration.ofMinutes(2))
        )
    }
}
