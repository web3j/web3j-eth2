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
package org.web3j.beacon.api.schema

/**
 *
 * @param index
 * @param balance
 * @param status Possible statuses: - **pending_initialized** - When the first deposit is processed, but not enough funds are available (or not yet the end of the first epoch) to get validator into the activation queue. - **pending_queued** - When validator is waiting to get activated, and have enough funds etc. while in the queue, validator activation epoch keeps changing until it gets to the front and make it through (finalization is a requirement here too). - **active_ongoing** - When validator must be attesting, and have not initiated any exit. - **active_exiting** - When validator is still active, but filed a voluntary request to exit. - **active_slashed** - When validator is still active, but have a slashed status and is scheduled to exit. - **exited_unslashed** - When validator has reached reguler exit epoch, not being slashed, and doesn't have to attest any more, but cannot withdraw yet. - **exited_slashed** - When validator has reached reguler exit epoch, but was slashed, have to wait for a longer withdrawal period. - **withdrawal_possible** - After validator has exited, a while later is permitted to move funds, and is truly out of the system. - **withdrawal_done** - (not possible in phase0, except slashing full balance) - actually having moved funds away  [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ)
 * @param validator
 */
data class GetStateValidatorsResponseData(

    val index: AllOfGetStateValidatorsResponseDataIndex? = null,
    val balance: AllOfGetStateValidatorsResponseDataBalance? = null,
        /* Possible statuses: - **pending_initialized** - When the first deposit is processed, but not enough funds are available (or not yet the end of the first epoch) to get validator into the activation queue. - **pending_queued** - When validator is waiting to get activated, and have enough funds etc. while in the queue, validator activation epoch keeps changing until it gets to the front and make it through (finalization is a requirement here too). - **active_ongoing** - When validator must be attesting, and have not initiated any exit. - **active_exiting** - When validator is still active, but filed a voluntary request to exit. - **active_slashed** - When validator is still active, but have a slashed status and is scheduled to exit. - **exited_unslashed** - When validator has reached reguler exit epoch, not being slashed, and doesn't have to attest any more, but cannot withdraw yet. - **exited_slashed** - When validator has reached reguler exit epoch, but was slashed, have to wait for a longer withdrawal period. - **withdrawal_possible** - After validator has exited, a while later is permitted to move funds, and is truly out of the system. - **withdrawal_done** - (not possible in phase0, except slashing full balance) - actually having moved funds away  [Validator status specification](https://hackmd.io/ofFJ5gOmQpu1jjHilHbdQQ)  */
    val status: String? = null,
    val validator: ValidatorResponseValidator? = null
)
