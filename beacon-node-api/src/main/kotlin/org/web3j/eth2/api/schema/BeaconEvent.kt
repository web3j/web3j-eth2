package org.web3j.eth2.api.schema

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * SSE event from a Beacon Node.
 * 
 * See also: [Server-sent_events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format)
 */
data class BeaconEvent(
    val slot: Slot,
    val block: Root,
    val state: String,
    @JsonProperty("epoch_transition")
    val epochTransition: Boolean
)
