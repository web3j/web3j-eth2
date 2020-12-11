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

import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy
import org.testcontainers.containers.wait.strategy.Wait
import java.io.File
import java.time.Duration
import java.time.Instant

class TekuContainer : DockerComposeContainer<TekuContainer>(
    File("src/integration-test/resources/teku/docker-compose.yml")
) {
    init {
        val waitStrategy = (Wait.forHttp("/eth/v1/node/health")
            .withStartupTimeout(Duration.ofMinutes(10)) as HttpWaitStrategy)
            .withReadTimeout(Duration.ofMinutes(1))
            .forPort(LISTEN_PORT)

        for (index in 1..4) {
            withExposedService("teku$index", LISTEN_PORT, waitStrategy)
            withLogConsumer("teku$index") { print(it.utf8String) }
        }
        withPull(true)
        withEnv(
            mapOf(
                "USER" to "root",
                "GENESIS_TIME" to Instant.now()
                    .plusSeconds(30)
                    .epochSecond.toString()
            )
        )
    }

    companion object {
        const val LISTEN_PORT = 5051
    }
}
