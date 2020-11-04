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

import org.web3j.eth2.client.infrastructure.ApiClient
import org.web3j.eth2.client.infrastructure.ClientError
import org.web3j.eth2.client.infrastructure.ClientException
import org.web3j.eth2.client.infrastructure.RequestConfig
import org.web3j.eth2.client.infrastructure.RequestMethod
import org.web3j.eth2.client.infrastructure.ResponseType
import org.web3j.eth2.client.infrastructure.ServerError
import org.web3j.eth2.client.infrastructure.ServerException
import org.web3j.eth2.client.infrastructure.Success
import org.web3j.eth2.client.models.GetDepositContractResponse
import org.web3j.eth2.client.models.GetForkScheduleResponse
import org.web3j.eth2.client.models.GetSpecResponse

class ConfigResource(basePath: String = "{server_url}") : ApiClient(basePath) {

    /**
     * Get deposit contract address.
     * Retrieve deposit contract address and genesis fork version.
     * @return GetDepositContractResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getDepositContract(): GetDepositContractResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/config/deposit_contract"
        )
        val response = request<GetDepositContractResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetDepositContractResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get scheduled upcoming forks.
     * Retrieve all scheduled upcoming forks this node is aware of.
     * @return GetForkScheduleResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getForkSchedule(): GetForkScheduleResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/config/fork_schedule"
        )
        val response = request<GetForkScheduleResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetForkScheduleResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }

    /**
     * Get spec params.
     * Retrieve specification configuration used on this node. [Specification params list](https://github.com/ethereum/eth2.0-specs/blob/v1.0.0-rc.0/configs/mainnet/phase0.yaml)  Values are returned with following format:   - any value starting with 0x in the spec is returned as a hex string   - numeric values are returned as a quoted integer
     * @return GetSpecResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getSpec(): GetSpecResponse {

        val localVariableConfig = RequestConfig(
                RequestMethod.GET,
                "/eth/v1/config/spec"
        )
        val response = request<GetSpecResponse>(
                localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetSpecResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException((response as ClientError<*>).body as? String
                    ?: "Client error")
            ResponseType.ServerError -> throw ServerException((response as ServerError<*>).message
                    ?: "Server error")
        }
    }
}
