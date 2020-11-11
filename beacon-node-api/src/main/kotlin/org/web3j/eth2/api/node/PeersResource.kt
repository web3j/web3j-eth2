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
