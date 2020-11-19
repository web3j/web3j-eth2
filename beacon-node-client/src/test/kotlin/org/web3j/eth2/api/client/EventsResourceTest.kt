package org.web3j.eth2.api.client

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.web3j.eth2.api.schema.BeaconEventType
import java.util.EnumSet
import java.util.concurrent.CountDownLatch
import java.util.function.Consumer

@DisplayName("/eth/v1/events")
class EventsResourceTest : BeaconNodeApiTest() {

    @Test
    @Timeout(60)
    @DisplayName("GET /")
    fun `subscribe to node events`() {
        val countdownLatch = CountDownLatch(1)
        val topics = EnumSet.of(BeaconEventType.FINALIZED_CHECKPOINT)
        
        // The node will send a finalized checkpoint soon after
        client.events.onEvent(topics, Consumer { _ ->
            countdownLatch.countDown()
        })
        countdownLatch.await()
    }
}
