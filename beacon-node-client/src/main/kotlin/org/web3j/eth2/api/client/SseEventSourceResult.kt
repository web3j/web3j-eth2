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

import mu.KLogging
import org.web3j.eth2.api.schema.AttestationEvent
import org.web3j.eth2.api.schema.BeaconEvent
import org.web3j.eth2.api.schema.BeaconEventType
import org.web3j.eth2.api.schema.BlockEvent
import org.web3j.eth2.api.schema.ChainReorganizedEvent
import org.web3j.eth2.api.schema.FinalizedCheckpointEvent
import org.web3j.eth2.api.schema.HeadEvent
import org.web3j.eth2.api.schema.VoluntaryExitEvent
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import javax.ws.rs.sse.SseEventSource

internal class SseEventSourceResult(
    private val source: SseEventSource,
    onEvent: Consumer<BeaconEvent>
) : CompletableFuture<Void>() {
    init {
        source.register(
            {
                val eventType: Class<out BeaconEvent> =
                    when (BeaconEventType.fromString(it.name)) {
                        BeaconEventType.HEAD -> HeadEvent::class.java
                        BeaconEventType.BLOCK -> BlockEvent::class.java
                        BeaconEventType.ATTESTATION -> AttestationEvent::class.java
                        BeaconEventType.VOLUNTARY_EXIT -> VoluntaryExitEvent::class.java
                        BeaconEventType.FINALIZED_CHECKPOINT -> FinalizedCheckpointEvent::class.java
                        BeaconEventType.CHAIN_REORG -> ChainReorganizedEvent::class.java
                    }
                onEvent.accept(it.readData(eventType) as BeaconEvent)
            },
            { completeExceptionally(it) },
            { complete(null) }
        )
        whenComplete { _, _ ->
            // Close the source gracefully by client
            if (source.isOpen) source.close()
            logger.debug { "SSE event source closed." }
        }
    }

    fun open() {
        Thread {
            source.open()
            while (source.isOpen) {
                logger.debug { "Listening on SSE event source..." }
                Thread.sleep(5000)
            }
        }.start()
    }

    companion object : KLogging()
}
