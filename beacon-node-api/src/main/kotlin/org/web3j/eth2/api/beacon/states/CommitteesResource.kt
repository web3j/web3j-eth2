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

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.Epoch
import org.web3j.eth2.api.schema.Committee
import org.web3j.eth2.api.schema.Slot
import org.web3j.eth2.api.schema.ValidatorIndex
import javax.ws.rs.GET
import javax.ws.rs.QueryParam

interface CommitteesResource {

    /**
     * Retrieves all committees for the given state.
     *
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): BeaconResponse<List<Committee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Fetch committees for the given epoch.
     *              If not present then the committees for the epoch of the state will be obtained.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, epoch, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByEpoch(@QueryParam("epoch") epoch: Epoch): BeaconResponse<List<Committee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Fetch committees for the given epoch.
     *              If not present then the committees for the epoch of the state will be obtained.
     * @param index Restrict returned values to those matching the supplied committee index.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, index, epoch, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByEpochAndIndex(
        @QueryParam("epoch") epoch: Epoch,
        @QueryParam("index") index: ValidatorIndex
    ): BeaconResponse<List<Committee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Fetch committees for the given epoch.
     *              If not present then the committees for the epoch of the state will be obtained.
     * @param slot Restrict returned values to those matching the supplied slot.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, epoch, slot, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByEpochAndSlot(
        @QueryParam("epoch") epoch: Epoch,
        @QueryParam("slot") slot: Slot
    ): BeaconResponse<List<Committee>>

    /**
     * Retrieves the committees for the given state at the given epoch.
     *
     * @param epoch Fetch committees for the given epoch.
     *              If not present then the committees for the epoch of the state will be obtained.
     * @param index Restrict returned values to those matching the supplied committee index.
     * @param slot Restrict returned values to those matching the supplied slot.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID, index, epoch, slot, or combination thereof.
     * @throws javax.ws.rs.NotFoundException State not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByEpochAndIndexAndSlot(
        @QueryParam("epoch") epoch: Epoch,
        @QueryParam("index") index: ValidatorIndex,
        @QueryParam("slot") slot: Slot
    ): BeaconResponse<List<Committee>>
}
