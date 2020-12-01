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
package org.web3j.eth2.api.schema

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * SSE event from a Beacon Node.
 *
 * See also: [Server-sent_events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format)
 */
sealed class BeaconEvent(
    val type: BeaconEventType
)

/**
 * The node has finished processing, resulting in a new head.
 */
data class HeadEvent(
    val slot: Slot,
    val block: Root,
    val state: Root,
    @JsonProperty("epoch_transition")
    val epochTransition: Boolean,
    @JsonProperty("previous_duty_dependent_root")
    val previousDutyDependentRoot: Root,
    @JsonProperty("current_duty_dependent_root")
    val currentDutyDependentRoot: Root
) : BeaconEvent(BeaconEventType.HEAD)

/**
 * The node has received a block (from P2P or API).
 */
data class BlockEvent(
    val slot: Slot,
    val block: Root
) : BeaconEvent(BeaconEventType.BLOCK)

/**
 * The node has received an attestation (from P2P or API).
 */
data class AttestationEvent(
    @JsonProperty("aggregation_bits")
    val aggregationBits: Hex,
    val signature: BLSSignature,
    val `data`: AttestationData
) : BeaconEvent(BeaconEventType.ATTESTATION)

/**
 * The node has received a voluntary exit (from P2P or API).
 */
data class VoluntaryExitEvent(
    val message: VoluntaryExit,
    val signature: BLSSignature
) : BeaconEvent(BeaconEventType.VOLUNTARY_EXIT)

/**
 * Finalized checkpoint has been updated.
 */
data class FinalizedCheckpointEvent(
    val block: Root,
    val state: Root,
    val epoch: Epoch
) : BeaconEvent(BeaconEventType.FINALIZED_CHECKPOINT)

/**
 * The node has reorganized its chain.
 */
data class ChainReorganizedEvent(
    val slot: Slot,
    val depth: Uint64,
    val old_head_block: Root,
    val new_head_block: Root,
    val old_head_state: Root,
    val new_head_state: Root,
    val epoch: Epoch
) : BeaconEvent(BeaconEventType.CHAIN_REORG)
