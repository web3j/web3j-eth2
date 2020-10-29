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
package org.web3j.eth2.api.models

/**
 * The [`IndexedAttestation`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#indexedattestation) object from the Eth2.0 spec.
 * @param attestingIndices Attesting validator indices
 * @param signature
 * @param &#x60;data&#x60;
 */
data class Ethv1beaconpoolattesterSlashingsAttestation1(

        /* Attesting validator indices */
        val attestingIndices: Array<String>? = null,
        val signature: AllOfethv1beaconpoolattesterSlashingsAttestation1Signature? = null,
        val `data`: Ethv1beaconpoolattestationsData? = null
) 