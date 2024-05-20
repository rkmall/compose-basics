package com.rm.compose_fundamentals.topics.t8_mvi.favorite

import com.rm.compose_fundamentals.topics.t8_mvi.base.Reducer

class FavoriteReducer : Reducer<FavoriteState, FavoriteAction> {

    override fun reduce(state: FavoriteState, action: FavoriteAction): FavoriteState {
        return when (action) {
            FavoriteAction.Favorite -> state.copy(favorite = true)
            FavoriteAction.UnFavorite -> state.copy(favorite = false)
        }
    }
}