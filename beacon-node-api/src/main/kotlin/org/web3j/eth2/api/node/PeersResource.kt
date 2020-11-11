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
package org.web3j.eth2.api.node

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.Peer
import org.web3j.eth2.api.schema.PeerId
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface PeersResource {

    /**
     * Retrieves data about the node's network peers.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): BeaconResponse<List<Peer>>

    /**
     * Retrieves data about the given peer.
     *
     * @throws javax.ws.rs.BadRequestException The peer ID supplied could not be parsed.
     * @throws javax.ws.rs.NotFoundException Peer not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{peer_id}")
    fun findById(@PathParam("peer_id") peerId: PeerId): BeaconResponse<Peer>
}
