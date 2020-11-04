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
package org.web3j.beacon.api.resources.beacon

import org.web3j.beacon.api.schema.GetStateValidatorResponse
import org.web3j.beacon.api.schema.GetStateValidatorsResponse
import org.web3j.beacon.api.schema.ValidatorStatus
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
     */
    @GET
    @Path("{validator_id}")
    fun findById(@PathParam("validator_id") validatorId: String): GetStateValidatorResponse

    /**
     * Returns filterable list of validators with their balance, status and index.
     *
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional)
     * @param status [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ) (optional)
     */
    @GET
    fun findBy(
        @QueryParam("id") vararg id: String,
        @QueryParam("status") status: EnumSet<ValidatorStatus> = EnumSet.noneOf(ValidatorStatus::class.java)
    ): GetStateValidatorsResponse
}
