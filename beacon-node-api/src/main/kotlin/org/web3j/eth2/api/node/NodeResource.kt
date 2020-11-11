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
package org.web3j.eth2.api.node

import org.web3j.eth2.api.schema.NetworkIdentity
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Response

/**
 * Endpoints to query node related information.
 */
interface NodeResource {
    /**
     * Returns node health status in http status codes. Useful for load balancers.
     *  - `200`: Node is ready.
     *  - `206`: Node is syncing but can serve incomplete data.
     * 
     * @throws javax.ws.rs.ServiceUnavailableException Node not initialized or having issues.
     */
    @get:GET
    @get:Path("health")
    val health: Response

    @get:Path("peers")
    val peers: PeersResource
    
    /**
     * Retrieves data about the node's network presence.
     */
    @get:GET
    @get:Path("identity")
    val networkIdentity: NetworkIdentity

    /**
     * Get version string of the running beacon node.
     * Requests that the beacon node identify information about its implementation in a format similar to a  [HTTP User-Agent](https://tools.ietf.org/html/rfc7231#section-5.5.3) field.
     * @return GetVersionResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getNodeVersion(): GetVersionResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/node/version"
        )
        val response = request<GetVersionResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetVersionResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get node syncing status
     * Requests the beacon node to describe if it&#x27;s currently syncing or not, and if it is, what block it is up to.
     * @return GetSyncingStatusResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getSyncingStatus(): GetSyncingStatusResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/node/syncing"
        )
        val response = request<GetSyncingStatusResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetSyncingStatusResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }
}
