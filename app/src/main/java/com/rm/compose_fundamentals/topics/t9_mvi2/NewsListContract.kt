package com.rm.compose_fundamentals.topics.t9_mvi2

import com.rm.compose_fundamentals.topics.t9_mvi2.data.News


sealed class NewsViewState {
    data object Default : NewsViewState()
    data class DataReady(val newList: List<News>) : NewsViewState()
}

sealed class NewsEvent() {
    data class Message(val message: String) : NewsEvent()
}

sealed class NewsAction() {
    data class GetData(val domain: String) : NewsAction()
}