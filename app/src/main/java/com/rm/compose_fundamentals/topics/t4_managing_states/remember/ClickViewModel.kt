package com.rm.compose_fundamentals.topics.t4_managing_states.remember

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class ClickViewModel : ViewModel() {

    private val _clicks = MutableStateFlow(0)
    val clicks = _clicks.asStateFlow()

    private var job: Job? = null

    fun incrementClick() {
        _clicks.value += 1
    }

    // For testing
    fun updateClick() {
        job?.cancel()
        job = viewModelScope.launch {
            delay(2000)
            _clicks.value += 1
        }
    }

    private val texts = listOf("apple", "ball", "cat", "dog", "elf")

    private val _text = MutableStateFlow(texts[0])
    val text = _text.asStateFlow()

    fun updateText() {
        val random = Random.nextInt(0, 5)
        _text.update { texts[random] }
    }
}