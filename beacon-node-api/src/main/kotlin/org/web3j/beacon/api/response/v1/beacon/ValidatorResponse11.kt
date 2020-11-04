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
package org.web3j.beacon.api.response.v1.beacon

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.MoreObjects
import io.swagger.v3.oas.annotations.media.Schema
import tech.pegasys.teku.api.schema.SchemaConstants.EXAMPLE_UINT64
import tech.pegasys.teku.api.schema.Validator
import tech.pegasys.teku.datastructures.state.BeaconState
import tech.pegasys.teku.datastructures.util.BeaconStateUtil.compute_epoch_at_slot
import tech.pegasys.teku.infrastructure.unsigned.UInt64
import tech.pegasys.teku.util.config.Constants.FAR_FUTURE_EPOCH
import java.util.Objects
import java.util.Optional

class ValidatorResponse @JsonCreator constructor(
    @JsonProperty("index") index: UInt64,
    @JsonProperty("balance") balance: UInt64,
    @JsonProperty("status") status: ValidatorStatus,
    @JsonProperty("validator") validator: Validator
) {
    @JsonProperty("index")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Index of validator in validator registry.")
    val index: UInt64

    @JsonProperty("balance")
    @Schema(type = "string", example = EXAMPLE_UINT64, description = "Current validator balance in gwei.")
    val balance: UInt64

    @JsonProperty("status")
    val status: ValidatorStatus

    @JsonProperty("validator")
    val validator: Validator
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ValidatorResponse
        return (index == that.index &&
                balance == that.balance &&
                status == that.status && validator == that.validator)
    }

    override fun hashCode(): Int {
        return Objects.hash(index, balance, status, validator)
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("index", index)
            .add("balance", balance)
            .add("status", status)
            .add("validator", validator)
            .toString()
    }

    companion object {
        fun fromState(
            state: BeaconState,
            index: Int
        ): Optional<ValidatorResponse> {
            if (index >= state.getValidators().size()) {
                return Optional.empty()
            }
            val validatorInternal: tech.pegasys.teku.datastructures.state.Validator = state.getValidators().get(index)
            val current_epoch: UInt64 = compute_epoch_at_slot(state.getSlot())
            return Optional.of(
                ValidatorResponse(
                    UInt64.valueOf(index),
                    state.getBalances().get(index),
                    getValidatorStatus(current_epoch, validatorInternal),
                    Validator(validatorInternal)
                )
            )
        }

        fun getValidatorStatus(
            state: BeaconState,
            validatorIndex: Int?
        ): ValidatorStatus {
            return getValidatorStatus(
                compute_epoch_at_slot(state.getSlot()), state.getValidators().get(validatorIndex)
            )
        }

        fun getValidatorStatus(
            epoch: UInt64,
            validator: tech.pegasys.teku.datastructures.state.Validator
        ): ValidatorStatus {
            // pending
            if (validator.getActivation_epoch().isGreaterThan(epoch)) {
                return if (validator.getActivation_eligibility_epoch()
                        .equals(FAR_FUTURE_EPOCH)
                ) ValidatorStatus.pending_initialized else ValidatorStatus.pending_queued
            }
            // active
            if (validator.getActivation_epoch().isLessThanOrEqualTo(epoch) &&
                epoch.isLessThan(validator.getExit_epoch())
            ) {
                if (validator.getExit_epoch().equals(FAR_FUTURE_EPOCH)) {
                    return ValidatorStatus.active_ongoing
                }
                return if (validator.isSlashed()) ValidatorStatus.active_slashed else ValidatorStatus.active_exiting
            }

            // exited
            if (validator.getExit_epoch().isLessThanOrEqualTo(epoch) &&
                epoch.isLessThan(validator.getWithdrawable_epoch())
            ) {
                return if (validator.isSlashed()) ValidatorStatus.exited_slashed else ValidatorStatus.exited_unslashed
            }

            // withdrawal
            if (validator.getWithdrawable_epoch().isLessThanOrEqualTo(epoch)) {
                return if (validator.getEffective_balance()
                        .isGreaterThan(UInt64.ZERO)
                ) ValidatorStatus.withdrawal_possible else ValidatorStatus.withdrawal_done
            }
            throw IllegalStateException("Unable to determine validator status")
        }
    }

    init {
        this.index = index
        this.balance = balance
        this.status = status
        this.validator = validator
    }
}
