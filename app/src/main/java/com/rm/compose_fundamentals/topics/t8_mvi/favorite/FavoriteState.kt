package com.rm.compose_fundamentals.topics.t8_mvi.favorite

import com.rm.compose_fundamentals.topics.t8_mvi.base.State


data class FavoriteState(
    val favorite: Boolean
) : State