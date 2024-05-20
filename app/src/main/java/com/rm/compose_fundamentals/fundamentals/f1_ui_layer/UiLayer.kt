package com.rm.compose_fundamentals.fundamentals.f1_ui_layer

import androidx.compose.runtime.Composable


// Ui State
data class NewsUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItems: List<NewsItemUiState> = listOf(),
    val userMessages: List<Message> = listOf()
)

data class NewsItemUiState(
    val title: String,
    val body: String,
    val bookmarked: Boolean = false
)

data class Message(
    val id: Int,
    val content: String
)
