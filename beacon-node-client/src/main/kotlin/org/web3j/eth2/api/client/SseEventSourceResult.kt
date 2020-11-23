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
    
    companion object: KLogging()
}
