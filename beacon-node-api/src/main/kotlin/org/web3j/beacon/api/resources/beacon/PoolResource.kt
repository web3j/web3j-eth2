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

import org.web3j.beacon.api.schema.Body1
import org.web3j.beacon.api.schema.Body2
import org.web3j.beacon.api.schema.Body3
import org.web3j.beacon.api.schema.Body4
import org.web3j.beacon.api.schema.GetPoolAttestationsResponse
import org.web3j.beacon.api.schema.GetPoolAttesterSlashingsResponse
import org.web3j.beacon.api.schema.GetPoolProposerSlashingsResponse
import org.web3j.beacon.api.schema.GetPoolVoluntaryExitsResponse

interface PoolResource {

    /**
     * Get Attestations from operations pool
     * Retrieves attestations known by the node but not necessarily incorporated into any block
     * @param slot (optional)
     * @param committeeIndex (optional)
     * @return GetPoolAttestationsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getPoolAttestations(slot: String? = null, committeeIndex: String? = null): GetPoolAttestationsResponse {
        val localVariableQuery: MultiValueMap =
            mapOf("slot" to listOf("$slot"), "committee_index" to listOf("$committeeIndex"))
        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/pool/attestations", query = localVariableQuery
        )
        val response = request<GetPoolAttestationsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetPoolAttestationsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get AttesterSlashings from operations pool
     * Retrieves attester slashings known by the node but not necessarily incorporated into any block
     * @return GetPoolAttesterSlashingsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getPoolAttesterSlashings(): GetPoolAttesterSlashingsResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/pool/attester_slashings"
        )
        val response = request<GetPoolAttesterSlashingsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetPoolAttesterSlashingsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get ProposerSlashings from operations pool
     * Retrieves proposer slashings known by the node but not necessarily incorporated into any block
     * @return GetPoolProposerSlashingsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getPoolProposerSlashings(): GetPoolProposerSlashingsResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/pool/proposer_slashings"
        )
        val response = request<GetPoolProposerSlashingsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetPoolProposerSlashingsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Get SignedVoluntaryExit from operations pool
     * Retrieves voluntary exits known by the node but not necessarily incorporated into any block
     * @return GetPoolVoluntaryExitsResponse
     */
    @Suppress("UNCHECKED_CAST")
    fun getPoolVoluntaryExits(): GetPoolVoluntaryExitsResponse {

        val localVariableConfig = RequestConfig(
            RequestMethod.GET,
            "/eth/v1/beacon/pool/voluntary_exits"
        )
        val response = request<GetPoolVoluntaryExitsResponse>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> (response as Success<*>).data as GetPoolVoluntaryExitsResponse
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Submit Attestation object to node
     * Submits Attestation object to node. If attestation passes all validation constraints, node MUST publish attestation on appropriate subnet.
     * @param body
     * @return void
     */
    fun submitPoolAttestations(body: Body1) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/eth/v1/beacon/pool/attestations"
        )
        val response = request<Any?>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Submit AttesterSlashing object to node&#x27;s pool
     * Submits AttesterSlashing object to node&#x27;s pool and if passes validation node MUST broadcast it to network.
     * @param body
     * @return void
     */
    fun submitPoolAttesterSlashings(body: Body2) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/eth/v1/beacon/pool/attester_slashings"
        )
        val response = request<Any?>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Submit ProposerSlashing object to node&#x27;s pool
     * Submits ProposerSlashing object to node&#x27;s pool and if passes validation  node MUST broadcast it to network.
     * @param body
     * @return void
     */
    fun submitPoolProposerSlashings(body: Body3) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/eth/v1/beacon/pool/proposer_slashings"
        )
        val response = request<Any?>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }

    /**
     * Submit SignedVoluntaryExit object to node&#x27;s pool
     * Submits SignedVoluntaryExit object to node&#x27;s pool and if passes validation node MUST broadcast it to network.
     * @param body
     * @return void
     */
    fun submitPoolVoluntaryExit(body: Body4) {
        val localVariableBody: Any? = body

        val localVariableConfig = RequestConfig(
            RequestMethod.POST,
            "/eth/v1/beacon/pool/voluntary_exits"
        )
        val response = request<Any?>(
            localVariableConfig
        )

        return when (response.responseType) {
            ResponseType.Success -> Unit
            ResponseType.Informational -> TODO()
            ResponseType.Redirection -> TODO()
            ResponseType.ClientError -> throw ClientException(
                (response as ClientError<*>).body as? String
                    ?: "Client error"
            )
            ResponseType.ServerError -> throw ServerException(
                (response as ServerError<*>).message
                    ?: "Server error"
            )
        }
    }
}
