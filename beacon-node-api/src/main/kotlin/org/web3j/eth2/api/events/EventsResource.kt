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
package org.web3j.eth2.api.events

import org.web3j.eth2.api.schema.BeaconEvent
import org.web3j.eth2.api.schema.BeaconEventType
import java.util.EnumSet
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/**
 * Set of endpoints to for event subscription.
 */
interface EventsResource {

    /**
     * Provides endpoint to subscribe to beacon node Server-Sent-Events stream.
     * Consumers should use [eventsource](https://html.spec.whatwg.org/multipage/server-sent-events.html#the-eventsource-interface)
     * implementation to listen on those events.
     *
     * @param topics Event types to subscribe to.
     * @param onEvent Event reception callback.
     * @return A [CompletableFuture] that is completed normally when the SSE complete,
     * and exceptionally upon any error or cancellation.
     *
     * @throws javax.ws.rs.BadRequestException The topics supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    fun onEvent(topics: EnumSet<BeaconEventType>, onEvent: Consumer<BeaconEvent>): CompletableFuture<Void>
}
