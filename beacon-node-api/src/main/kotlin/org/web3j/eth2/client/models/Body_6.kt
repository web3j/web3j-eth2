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
 *
 * @param committeeIndex
 * @param committeesAtSlot
 * @param slot
 * @param isAggregator Signals to BN that a validator on the VC has been chosed for aggregator role.
 */
data class Body6(

        val committeeIndex: String? = null,
        val committeesAtSlot: AllOfbody6CommitteesAtSlot? = null,
        val slot: AllOfbody6Slot? = null,
        /* Signals to BN that a validator on the VC has been chosed for aggregator role. */
        val isAggregator: Boolean? = null
) 