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

import org.web3j.beacon.api.schema.GetBlockHeaderResponse
import org.web3j.beacon.api.schema.GetBlockHeadersResponse
import org.web3j.beacon.api.schema.ParentRoot
import org.web3j.beacon.api.schema.Slot1
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

interface HeadersResource {

    /**
     * Retrieves block headers matching given query. By default it will fetch current head slot blocks.
     *
     * @param slot (optional)
     * @param parentRoot (optional)
     * @return GetBlockHeadersResponse
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    fun getBlockHeaders(slot: Slot1? = null, parentRoot: ParentRoot? = null): GetBlockHeadersResponse

    /**
     * Retrieves block header for given block id.
     *
     * @param blockId Block identifier. Can be one of: `head` (canonical head in node's view),
     * `genesis`, `finalized`, `<slot>`, `<hex encoded blockRoot with 0x prefix>`.
     * @return GetBlockHeaderResponse
     * @throws javax.ws.rs.NotFoundException Block not found.
     * @throws javax.ws.rs.BadRequestException The block ID supplied could not be parsed.
     * @throws javax.ws.rs.InternalServerErrorException Beacon node internal error.
     */
    @GET
    @Path("{block_id}")
    fun getBlockHeader(@PathParam("block_id") blockId: String): GetBlockHeaderResponse
}
