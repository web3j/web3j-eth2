package org.web3j.eth2.api.schema

enum class NamedStateId {
    HEAD,
    GENESIS,
    FINALIZED,
    JUSTIFIED;

    override fun toString() = name.toLowerCase()
    fun fromString(value: String) = valueOf(value.toUpperCase())
}
