package org.web3j.eth2.api.schema

/**
 * Generic error response.
 */
data class ErrorResponse(
    val title: String,
    val status: Int,
    val type: String,
    val details: List<String> = emptyList()
)
