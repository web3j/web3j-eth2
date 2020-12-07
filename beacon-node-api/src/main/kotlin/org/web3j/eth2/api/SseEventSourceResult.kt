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

import mu.KLogging
import org.web3j.eth2.api.schema.AttestationEvent
import org.web3j.eth2.api.schema.BeaconEvent
import org.web3j.eth2.api.schema.BeaconEventType
import org.web3j.eth2.api.schema.BeaconEventType.ATTESTATION
import org.web3j.eth2.api.schema.BeaconEventType.BLOCK
import org.web3j.eth2.api.schema.BeaconEventType.CHAIN_REORG
import org.web3j.eth2.api.schema.BeaconEventType.FINALIZED_CHECKPOINT
import org.web3j.eth2.api.schema.BeaconEventType.HEAD
import org.web3j.eth2.api.schema.BeaconEventType.VOLUNTARY_EXIT
import org.web3j.eth2.api.schema.BlockEvent
import org.web3j.eth2.api.schema.ChainReorganizedEvent
import org.web3j.eth2.api.schema.FinalizedCheckpointEvent
import org.web3j.eth2.api.schema.HeadEvent
import org.web3j.eth2.api.schema.VoluntaryExitEvent
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import javax.ws.rs.sse.InboundSseEvent
import javax.ws.rs.sse.SseEventSource

/**
 * Encapsulates SSE results and notifies a given [Consumer] of each received [BeaconEvent].
 */
internal class SseEventSourceResult(
    private val source: SseEventSource,
    onEvent: Consumer<BeaconEvent>
) : CompletableFuture<Void>() {
    init {
        source.register({
            if (it.name != null) {
                logger.info { "Received SSE event: $it" }
                val eventType = readEventType(it)
                onEvent.accept(it.readData(eventType))
            } else {
                logger.warn { "Received empty SSE event, will be ignored." }
            }
        },
            { completeExceptionally(it) },
            { complete(null) })
        whenComplete { _, error ->
            // Close the source gracefully by client
            if (source.isOpen) {
                source.close()
                logger.debug { "SSE event source closed." }
            }
            if (error != null) {
                logger.warn {
                    "SSE event source finished with exception: $error"
                }
            }
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

    private fun readEventType(it: InboundSseEvent): Class<out BeaconEvent> {
        return when (BeaconEventType.fromString(it.name)) {
            HEAD -> HeadEvent::class.java
            BLOCK -> BlockEvent::class.java
            ATTESTATION -> AttestationEvent::class.java
            VOLUNTARY_EXIT -> VoluntaryExitEvent::class.java
            FINALIZED_CHECKPOINT -> FinalizedCheckpointEvent::class.java
            CHAIN_REORG -> ChainReorganizedEvent::class.java
        }
    }

    companion object : KLogging()
}
