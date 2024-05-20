package com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.data

/**
 * Represent current UI state in terms of [quantity], [flavor], [dateOptions],
 * selected pickup [date] and [price]
 */
data class OrderUiState(
    val quantity: Int = 0,
    val flavor: String = "",
    val date: String = "",
    val price: String = "",
    val pickUpOptions: List<String> = listOf()
)