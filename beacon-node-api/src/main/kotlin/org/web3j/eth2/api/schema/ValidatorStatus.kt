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
package org.web3j.eth2.api.schema

/**
 * Status epochs, for reference:
 *
 * - `activation_eligibility_epoch`: When criteria for activation were met
 * - `activation_epoch`: When the validator is/was scheduled to activate. May change while in the activation queue.
 * - `exit_epoch`: When the validator is/was scheduled to exit. May change while exiting.
 * - `withdrawable_epoch`: When validator can withdraw funds
 *
 * From [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ).
 */
enum class ValidatorStatus {

    /**
     * Condition: `validator.activation_epoch <= current_epoch < validator.exit_epoch`
     */
    ACTIVE,

    /**
     * When you are still active, but filed a voluntary request to exit.
     *
     * Condition: `(validator.activation_epoch <= current_epoch) and (current_epoch < validator.exit_epoch < FAR_FUTURE_EPOCH) and (not validator.slashed)`
     */
    ACTIVE_EXITING,

    /**
     * When you must be attesting, and have not initiated any exit.
     *
     * Condition: `(validator.activation_epoch <= current_epoch) and (validator.exit_epoch == FAR_FUTURE_EPOCH)`
     */
    ACTIVE_ONGOING,

    /**
     * When you are still active, but have a slashed status, and are scheduled to exit.
     *
     * Condition: `(validator.activation_epoch <= current_epoch) and (current_epoch < validator.exit_epoch < FAR_FUTURE_EPOCH) and validator.slashed`
     */
    ACTIVE_SLASHED,

    /**
     * Condition: `(validator.exit_epoch <= current_epoch < validator.withdrawable_epoch)`
     */
    EXITED,

    /**
     * When you reach your exit epoch, but were slashed, have to wait for a longer withdrawal period,
     * and still will lose stake based on the 50% backward and forward context rule.
     *
     * Condition: `(validator.exit_epoch <= current_epoch < validator.withdrawable_epoch) and validator.slashed`
     */
    EXITED_SLASHED,

    /**
     * When you reach your reguler exit epoch, not being slashed, and you don’t have to attest any more,
     * but cannot withdraw yet.
     *
     * Condition: `(validator.exit_epoch <= current_epoch < validator.withdrawable_epoch) and (not validator.slashed)`
     */
    EXITED_UNSLASHED,

    /**
     * Condition: `validator.activation_epoch > current_epoch`
     */
    PENDING,

    /**
     * When the first deposit is processed, but not enough funds are available
     * (or not yet the end of the first epoch) to get into the activation queue.
     *
     * Condition: `validator.activation_eligibility_epoch == FAR_FUTURE_EPOCH`
     */
    PENDING_INITIALIZED,

    /**
     * When you are waiting to get activated, and have enough funds etc. while in the queue,
     * your activation epoch keeps changing until you get to the front and make it through
     * (finalization is a requirement here too).
     *
     * Condition: `(validator.activation_eligibility_epoch < FAR_FUTURE_EPOCH) and (validator.activation_epoch > current_epoch)`
     */
    PENDING_QUEUED,

    /**
     * Condition: `validator.withdrawable_epoch <= current_epoch`
     */
    WITHDRAWAL,

    /**
     * (not possible in phase0, except slashing full balance) - actually having moved funds away.
     *
     * Condition: `(validator.withdrawable_epoch <= current_epoch) and (balance == 0)`
     */
    WITHDRAWAL_DONE,

    /**
     *  After having exited, a while later you are permitted to move funds, and are truly out of the system.
     *
     *  Condition: `(validator.withdrawable_epoch <= current_epoch) and (balance != 0)`
     */
    WITHDRAWAL_POSSIBLE;

    override fun toString() = name.toLowerCase()

    companion object {
        @JvmStatic
        fun fromString(value: String) = valueOf(value.toUpperCase())
    }
}
