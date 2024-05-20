package com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.data

import com.rm.compose_fundamentals.R

object DataSource {
    val flavors = listOf(
        R.string.flavor_vanilla,
        R.string.flavor_chocolate,
        R.string.flavor_red_velvet,
        R.string.flavor_salted_caramel,
        R.string.flavor_coffee
    )

    val quantityOptions = listOf(
        Pair(R.string.quantity_one, 1),
        Pair(R.string.quantity_three, 3),
        Pair(R.string.quantity_six, 6),
        Pair(R.string.quantity_twelve, 12)
    )
}