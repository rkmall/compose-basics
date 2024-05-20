package com.rm.compose_fundamentals.topics.t3_effects.launchedeffect.launchedeffectviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel : ViewModel() {

    private val _state = MutableStateFlow("n/a")
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<ScreenEvents>()

    private val _effect: Channel<ScreenEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        handleEvents()
    }

    private fun handleEvents() {
        viewModelScope.launch {
            _event.collect { event -> // as per event type, setState or setEffect
                when(event) {
                    is ScreenEvents.UpdateText -> setState(event.text)

                    is ScreenEvents.ButtonClicked -> setEffect()
                }
            }
        }
    }

    fun setEvent(event: ScreenEvents) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun setState(state: String) {
        _state.value = state
    }

    private fun setEffect() {
        viewModelScope.launch {
            _effect.send(ScreenEffects.ShowSnackbar("Hello"))
        }
    }

    sealed class ScreenEvents {
        data class UpdateText(val text: String) : ScreenEvents()
        data object ButtonClicked : ScreenEvents()
    }

    sealed class ScreenEffects {
        data class ShowSnackbar(val message: String) : ScreenEffects()
        data class Navigate(val route: String) : ScreenEffects()
    }
}
