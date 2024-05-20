package com.rm.compose_fundamentals.topics.t4_managing_states

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun StateHoistingPreview() {
    ComposefundamentalsTheme {
        NoHoistingExample.ChatBubble(message = "Hello")
        HoistingWithinComposables.ConversationScreen()
    }
}


/**
 * No Hoisting. Composable with internal state.
 */
private object NoHoistingExample {
    @Composable
    fun ChatBubble(message: String) {
        var showDetails by rememberSaveable { // internal state stored and managed by this Composable
            mutableStateOf(false)
        }

        ClickableText(
            text = AnnotatedString(message),
            onClick = { showDetails = !showDetails }
        )

        if (showDetails) {
            Text(text = message)
        }
    }
}

/**
 * Hoisting within Composables.
 */
private object HoistingWithinComposables {
    var messages = listOf<Message>()

    @Composable
    fun UserInput(onMessageSent: () -> Unit) {
    }
    @Composable
    fun JumpToBottom(onClicked: () -> Unit) {
    }

    @Composable
    fun ConversationScreen() {
        val scope = rememberCoroutineScope()

        val lazyListState = rememberLazyListState() // LazyList state hoisted to this composable screen

        MessageList(messages, lazyListState) // reuse same state in MessageList

        UserInput(
            onMessageSent = { // apply UI logic to lazyListState
            scope.launch {
                lazyListState.scrollToItem(0)
            }
        })
    }

    @Composable
    private fun MessageList(
        messages: List<Message>,
        lazyListState: LazyListState = rememberLazyListState() // default remember state
    ) {
        LazyColumn(
            state = lazyListState // pass hoisted state to LazyColumn
        ) {
            items(messages, key = { message -> message.id }) {
                Message("10", "content", "12:12")
            }
        }

        val scope = rememberCoroutineScope()

        JumpToBottom(
            onClicked =  {
            scope.launch {
                lazyListState.scrollToItem(0) // UI logic being applied to lazyList
            }
        })
    }
}


private object ViewModelAsStateHolder {
    @Composable
    private fun MessagesList(
        messages: List<Message>,
        onSendMessage: (Message) -> Unit
    ) {}

    class ConversationViewModel(
        channelId: String,
        messagesRepository: MessagesRepository
    ) : ViewModel() {

        var inputMessage by mutableStateOf("") // Hoisted state in ViewModel
            private set

        val messages = messagesRepository
            .getLatestMessages(channelId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

        // Business logic
        fun sendMessage(message: Message) { /* ... */ }
    }

    @Composable
    private fun ConversationScreen(
        conversationViewModel: ConversationViewModel = viewModel()  // Inject ViewModel in Screen
    ) {
        var inputMessageState: String = conversationViewModel.inputMessage

        val messages by conversationViewModel.messages.collectAsStateWithLifecycle()

        ConversationScreen(
            messages = messages,
            onSendMessage = { message: Message ->
                conversationViewModel.sendMessage(message) }
        )
    }

    @Composable
    private fun ConversationScreen(
        messages: List<Message>,
        onSendMessage: (Message) -> Unit
    ) {
        MessagesList(messages, onSendMessage)
    }
}

data class Message(
    var id: String = "",
    var content: String = "",
    var timestamp: String = ""
)

abstract class MessagesRepository() {
    abstract fun getLatestMessages(channelId: String): StateFlow<List<Message>>
}



