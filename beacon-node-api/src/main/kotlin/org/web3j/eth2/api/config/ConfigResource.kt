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
package org.web3j.eth2.api.config

import org.web3j.eth2.api.schema.BeaconResponse
import org.web3j.eth2.api.schema.DepositContract
import org.web3j.eth2.api.schema.Fork
import javax.ws.rs.GET
import javax.ws.rs.Path

/**
 * Endpoints to query chain configuration, specification, and fork schedules.
 */
interface ConfigResource {

    /**
     * Retrieve deposit contract address and genesis fork version.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("deposit_contract")
    val depositContract: BeaconResponse<DepositContract>

    /**
     * Retrieve all scheduled upcoming forks this node is aware of.
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("fork_schedule")
    val forkSchedule: BeaconResponse<List<Fork>>

    /**
     * Retrieve specification configuration used on this node.
     *
     * Values are returned with following format:
     *   - Any value starting with `0x` in the spec is returned as a hex string.
     *   - Numeric values are returned as a quoted integer.
     *
     * @return Key value mapping of
     * [spec variables](https://github.com/ethereum/eth2.0-specs/blob/v1.0.0-rc.0/configs/mainnet/phase0.yaml).
     *
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @get:GET
    @get:Path("spec")
    val specification: BeaconResponse<Map<String, String>>
}
