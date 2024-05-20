package com.rm.compose_fundamentals.topics.t4_managing_states

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.saveable
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme

@Preview(showBackground = true)
@Composable
fun SavingUiStates() {
    ComposefundamentalsTheme {
        MessageScreen(Message("1", "Hello", "11:10"))
    }
}


@Composable
fun MessageScreen(message: Message) {
    var showDetails by rememberSaveable { mutableStateOf(true) }

    Column {
        ClickableText(
            text = AnnotatedString(message.content),
            onClick = { showDetails = !showDetails}
        )

        if (showDetails) {
            Text(text = message.timestamp)
        }
    }
}

@Composable
fun rememberLazyListState(
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0
): LazyListState {
    return rememberSaveable(saver = LazyListState.Saver) {
        LazyListState(initialFirstVisibleItemIndex, initialFirstVisibleItemScrollOffset)
    }
}


private object ComposeStateAndSavedStateHandle {

    class ConversationViewModel(
        savedStateHandle: SavedStateHandle
    ) : ViewModel() {

        var message by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
            mutableStateOf(TextFieldValue(""))
        }
            private set


        fun update(newMessage: TextFieldValue) {
            message = newMessage
        }
    }

    @Composable
    fun UserInput(viewModel: ConversationViewModel) {
        TextField(
            value = viewModel.message,
            onValueChange = { viewModel.update(it) })
    }
}