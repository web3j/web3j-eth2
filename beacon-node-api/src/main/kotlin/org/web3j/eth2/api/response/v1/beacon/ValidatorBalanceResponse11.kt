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
package org.web3j.eth2.api.response.v1.beacon

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.Objects
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64
import tech.pegasys.teku.datastructures.state.BeaconState
import tech.pegasys.teku.infrastructure.unsigned.UInt64

class ValidatorBalanceResponse @JsonCreator constructor(
    @JsonProperty("index") index: UInt64,
    @JsonProperty("balance") balance: UInt64
) {
    @JsonProperty("index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Index of validator in validator registry.")
    val index: UInt64

    @JsonProperty("balance")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Current validator balance in gwei.")
    val balance: UInt64
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is ValidatorBalanceResponse) return false
        val that = o
        return Objects.equal(index, that.index) && Objects.equal(balance, that.balance)
    }

    override fun hashCode(): Int {
        return Objects.hashCode(index, balance)
    }

    companion object {
        fun fromState(state: BeaconState, index: Int?): ValidatorBalanceResponse {
            return ValidatorBalanceResponse(UInt64.valueOf(index), state.getBalances().get(index))
        }
    }

    init {
        this.index = index
        this.balance = balance
    }
}
