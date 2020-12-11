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
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.web3j.eth2.api.schema.BeaconEventType
import java.util.EnumSet
import java.util.concurrent.CountDownLatch
import java.util.function.Consumer

abstract class EventsResourceTest(private val client: BeaconNodeApi) {

    @Test
    @Timeout(90)
    @DisplayName("GET /")
    fun `subscribe to node events`() {
        val countdownLatch = CountDownLatch(1)
        val topics = EnumSet.allOf(BeaconEventType::class.java)

        // The node should send an event soon after
        val future = client.events.onEvent(topics, Consumer {
            countdownLatch.countDown()
        })
        countdownLatch.await()
        future.complete(null)
    }
}
