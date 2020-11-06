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
package org.web3j.eth2.api.beacon.states

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.web3j.eth2.api.schema.Epoch
import org.web3j.eth2.api.schema.EpochCommittee
import org.web3j.eth2.api.schema.Index
import org.web3j.eth2.api.schema.Response
import org.web3j.eth2.api.schema.Slot
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

interface CommitteesResource {

    /**
     * Retrieves all committees for the given state.
     *
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): Response<List<EpochCommittee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, epoch, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{epoch}")
    @JsonDeserialize()
    fun findByEpoch(@PathParam("epoch") epoch: Epoch): Response<List<EpochCommittee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     * @param index Committee index.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, index, epoch, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{epoch}")
    fun findByEpochAndIndex(
        @PathParam("epoch") epoch: Epoch,
        @QueryParam("index") index: Index
    ): Response<List<EpochCommittee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, epoch, slot, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{epoch}")
    fun findByEpochAndSlot(
        @PathParam("epoch") epoch: Epoch,
        @QueryParam("slot") slot: Slot
    ): Response<List<EpochCommittee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Epoch for which to calculate committees. Defaults to beacon state epoch.
     * @param index Committee index.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, index, epoch, slot, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{epoch}")
    fun findByEpochAndIndexAndSlot(
        @PathParam("epoch") epoch: Epoch,
        @QueryParam("index") index: Index,
        @QueryParam("slot") slot: Slot
    ): Response<List<EpochCommittee>>
}