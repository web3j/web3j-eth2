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
package org.web3j.eth2.api.validator

import org.web3j.eth2.api.schema.BLSSignature
import org.web3j.eth2.api.schema.BeaconBlock
import org.web3j.eth2.api.schema.BeaconResponse
import javax.ws.rs.GET
import javax.ws.rs.QueryParam

interface BlockResource {

    /**
     * Produce a new block, without signature.
     *
     * Requests a beacon node to produce a valid block, which can then be signed by a validator.
     *
     * @param randaoReveal The validator's randao reveal value.
     * @param graffiti Arbitrary data validator wants to include in block. (optional)
     */
    @GET
    fun produceBlock(
        @QueryParam("randao_reveal") randaoReveal: BLSSignature,
        @QueryParam("graffiti") graffiti: String
    ): BeaconResponse<BeaconBlock>

    /**
     * Produce a new block, without signature.
     *
     * Requests a beacon node to produce a valid block, which can then be signed by a validator.
     *
     * @param randaoReveal The validator's randao reveal value.
     */
    @GET
    fun produceBlock(
        @QueryParam("randao_reveal") randaoReveal: BLSSignature
    ): BeaconResponse<BeaconBlock>
}
