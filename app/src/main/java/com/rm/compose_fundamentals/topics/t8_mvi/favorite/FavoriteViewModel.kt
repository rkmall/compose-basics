package com.rm.compose_fundamentals.topics.t8_mvi.favorite

import com.rm.compose_fundamentals.topics.t8_mvi.base.MVIBaseViewModel

class FavoriteViewModel(
    reducer: FavoriteReducer = FavoriteReducer(),
) : MVIBaseViewModel<FavoriteState, FavoriteAction>(
    reducer = reducer,
    stateInit = FavoriteState(false) // init value, favorite is false in the beginning
) {

    fun onFavorite() {
        emit(FavoriteAction.Favorite)
    }

    fun onUnFavorite() {
        emit(FavoriteAction.UnFavorite)
    }
}