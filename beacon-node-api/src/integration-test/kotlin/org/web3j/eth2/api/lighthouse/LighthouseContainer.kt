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
package org.web3j.eth2.api.lighthouse

import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.MountableFile.forClasspathResource
import java.time.Duration

class LighthouseContainer : GenericContainer<LighthouseContainer>("sigp/lighthouse:latest") {
    init {
        withExposedPorts(5052, 9000)
        withLogConsumer { print(it.utf8String) }
        withCreateContainerCmdModifier {
            it.withEntrypoint("/start.sh")
        }
        withCopyFileToContainer(
            forClasspathResource("lighthouse/start.sh", 755),
            "/start.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/vars.env", 755),
            "/vars.env"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/setup.sh", 755),
            "/setup.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/beacon_node.sh", 755),
            "/beacon_node.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/validator_client.sh", 755),
            "/validator_client.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/reset_genesis_time.sh", 755),
            "/reset_genesis_time.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/second_beacon_node.sh", 755),
            "/second_beacon_node.sh"
        )
        withCopyFileToContainer(
            forClasspathResource("lighthouse/clean.sh", 755),
            "/clean.sh"
        )
        waitingFor(
            Wait.forHttp("/lighthouse/health")
                .forStatusCode(200)
                .forPort(5052)
        )
        withStartupTimeout(Duration.ofMinutes(10))
    }
}
