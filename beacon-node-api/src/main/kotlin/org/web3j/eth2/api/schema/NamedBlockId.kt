package org.web3j.eth2.api.schema

enum class NamedBlockId {
    HEAD,
    GENESIS,
    FINALIZED;

    override fun toString() = name.toLowerCase()
    fun fromString(value: String) = valueOf(value.toUpperCase())
}
