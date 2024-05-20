package com.rm.compose_fundamentals.topics.t8_mvi.favorite

import com.rm.compose_fundamentals.topics.t8_mvi.base.Action

sealed interface FavoriteAction : Action {
    data object Favorite: FavoriteAction
    data object UnFavorite: FavoriteAction
}