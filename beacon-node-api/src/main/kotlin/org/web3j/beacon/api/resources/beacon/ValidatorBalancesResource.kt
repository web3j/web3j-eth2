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

import org.web3j.beacon.api.schema.GetStateValidatorBalancesResponse
import javax.ws.rs.GET

interface ValidatorBalancesResource {

    /**
     * Get validator balances from state.
     *
     * @param id Either hex encoded public key (with 0x prefix) or validator index (optional).
     * @return Returns filterable list of validator balances.
     */
    @GET
    fun findBy(vararg id: String): GetStateValidatorBalancesResponse
}
