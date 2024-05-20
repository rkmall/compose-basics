package com.rm.compose_fundamentals.topics.t9_mvi2.ui.base

import android.view.View
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A [ViewState] flows from ViewModel to Composable.
 * It describes the what screen state should be displayed right now.
 */
interface ViewState

/**
 * A [ViewEvent] flow from Viewmodel to Composable.
 * These are one-time events like showing Snackbar or Dialog etc.
 */
interface ViewEvent

/**
 * A [ViewAction] flows from Composable to ViewModel.
 * This is also known as MVI Intent.
 * When a user takes an action in UI, [ViewAction] represents the user's intent.
 */
interface ViewAction


abstract class BaseViewModel<UiState: ViewState, Event: ViewEvent, Action: ViewAction> : ViewModel()  {

    abstract val initialState: UiState
    abstract fun processAction(action: Action)

    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)


}