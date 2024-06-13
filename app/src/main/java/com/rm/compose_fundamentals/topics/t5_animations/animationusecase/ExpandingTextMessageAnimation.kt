package com.rm.compose_fundamentals.topics.t5_animations.animationusecase

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.topics.t7_components.card.MessageCard

@Composable
fun ExpandingTextMessageAnimation(message: Message) {
    var showDetail by remember { mutableStateOf(false) }

    MessageCard(
        modifier = Modifier.padding(16.dp)
    ) {
        ClickableText(
            text = buildAnnotatedString { append(message.content) },
            style = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(16.dp),
            onClick = {
                showDetail = !showDetail
            }
        )
        AnimatedVisibility (showDetail) {
            Text(
                text = message.timeStamp,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewClickableMessageAnimation() {
    val message = Message(
        "Hello there how are you? This is just a sample text message",
        "08:45 PM"
    )
    ExpandingTextMessageAnimation(message)
}


data class Message(
    val content: String,
    val timeStamp: String
)