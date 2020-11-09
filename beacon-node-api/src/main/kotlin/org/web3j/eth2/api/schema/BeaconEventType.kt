package org.web3j.eth2.api.schema

enum class BeaconEventType {
    HEAD,
    BLOCK,
    ATTESTATION,
    VOLUNTARY_EXIT,
    FINALIZED_CHECKPOINT,
    CHAIN_REORG;

    override fun toString() = name.toLowerCase()

    companion object {
        @JvmStatic
        fun fromString(value: String) = ValidatorStatus.valueOf(value.toUpperCase())
    }
}
