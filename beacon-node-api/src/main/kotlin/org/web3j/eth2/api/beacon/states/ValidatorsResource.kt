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

import org.web3j.eth2.api.schema.GetStateValidatorsResponse
import org.web3j.eth2.api.schema.ValidatorStatus
import java.util.EnumSet
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

interface ValidatorsResource {

    /**
     * Returns validator specified by state and id or public key along with status and balance.
     *
     * @param validatorId Either hex encoded public key (with 0x prefix) or validator index.
     *
     * @throws javax.ws.rs.BadRequestException Invalid state or validator ID.
     * @throws javax.ws.rs.NotFoundException State or validator not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @Path("{validator_id}")
    fun withId(@PathParam("validator_id") validatorId: String): ValidatorResource

    /**
     * Returns list of all validators with their balance, status and index.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findAll(): GetStateValidatorsResponse = findById(emptyArray())

    /**
     * Returns filterable list of validators with their balance, status and index.
     *
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional).
     *
     * @throws javax.ws.rs.BadRequestException Invalid state or validator ID.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findById(@QueryParam("id") id: Array<String>): GetStateValidatorsResponse =
        findByIdAndStatus(id, EnumSet.noneOf(ValidatorStatus::class.java))

    /**
     * Returns filterable list of validators with their balance, status and index.
     *
     * @param status [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ) (optional).
     *
     * @throws javax.ws.rs.BadRequestException Invalid state ID or status.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByStatus(@QueryParam("status") status: EnumSet<ValidatorStatus>): GetStateValidatorsResponse =
        findByIdAndStatus(emptyArray(), status)

    /**
     * Returns filterable list of validators with their balance, status and index.
     *
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional).
     * @param status [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ) (optional).
     *
     * @throws javax.ws.rs.BadRequestException Invalid state or validator ID, or status.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    fun findByIdAndStatus(
        @QueryParam("id") id: Array<String>,
        @QueryParam("status") status: EnumSet<ValidatorStatus>
    ): GetStateValidatorsResponse
}
