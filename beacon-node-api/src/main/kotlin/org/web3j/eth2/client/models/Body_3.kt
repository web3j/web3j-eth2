/**
 * Eth2 Beacon Node API
 * API specification for the beacon node, which enables users to query and participate in Ethereum 2.0 phase 0 beacon chain.
 *
 * OpenAPI spec version: v0.12.2
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package org.web3j.eth2.client.models

/**
 * The [`ProposerSlashing`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#proposerslashing) object from the Eth2.0 spec.
 * @param signedHeader1
 * @param signedHeader2
 */
data class Body3(

        val signedHeader1: Ethv1beaconpoolproposerSlashingsSignedHeader1? = null,
        val signedHeader2: Ethv1beaconpoolproposerSlashingsSignedHeader1? = null
) 