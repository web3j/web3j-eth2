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

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class Eth1Chain(@get:JsonValue val chainId: Int) {

    /**
     * Mainnet : Ethereum main network.
     */
    MAINNET(1),

    /**
     * Ropsten : Ethereum test network (PoW).
     */
    ROPSTEN(3),

    /**
     * Rinkeby : Ethereum test network (PoA).
     */
    RINKEBY(4),

    /**
     * Kovan : Ethereum test network (PoA).
     */
    KOVAN(42),

    /**
     * Ethereum Classic Mainnet : Ethereum Classic main network.
     */
    ETHEREUM(61),

    /**
     * Morden : Ethereum Classic test network.
     */
    MORDEN(62);

    companion object {

        @JsonCreator
        fun forChainId(chainId: Int): Eth1Chain {
            return values().find { it.chainId == chainId }
                ?: throw IllegalArgumentException("No chain defined for ID: $chainId")
        }
    }
}
