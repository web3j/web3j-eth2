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
 *
 * @param code Either specific error code in case of invalid request or http status code
 * @param message Message describing error
 * @param stacktraces Optional stacktraces, sent when node is in debug mode
 */
data class InlineResponse40011(

        /* Either specific error code in case of invalid request or http status code */
    val code: java.math.BigDecimal? = null,
        /* Message describing error */
    val message: String? = null,
        /* Optional stacktraces, sent when node is in debug mode */
    val stacktraces: Array<String>? = null
)
