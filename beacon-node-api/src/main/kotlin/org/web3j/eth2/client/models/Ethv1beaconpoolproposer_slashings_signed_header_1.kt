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
 * The [`SignedBeaconBlockHeader`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#signedbeaconblockheader) object envelope from the Eth2.0 spec.
 * @param message The [`BeaconBlockHeader`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblockheader) object from the Eth2.0 spec.
 * @param signature
 */
data class Ethv1beaconpoolproposerSlashingsSignedHeader1(

        /* The [`BeaconBlockHeader`](https://github.com/ethereum/eth2.0-specs/blob/v0.12.2/specs/phase0/beacon-chain.md#beaconblockheader) object from the Eth2.0 spec. */
        val message: AllOfethv1beaconpoolproposerSlashingsSignedHeader1Message? = null,
        val signature: String? = null
) 