package com.rm.compose_fundamentals.topics.t5_animations.animationusecase

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExpandingTextMessage(
    modifier: Modifier = Modifier,
    message: String,
    fontSize: TextUnit = 20.sp,
    color: Color = Color.Unspecified,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    defaultMaxLines: Int = 3
) {
    var showMore by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = message,
            fontSize = fontSize,
            style = textStyle,
            color = color,
            maxLines = if (expanded) Int.MAX_VALUE else defaultMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.hasVisualOverflow) {
                    showMore = true
                }
            },
            modifier = modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        )

        if (showMore) {
            ExpandableMessageTextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                buttonState = if (expanded) "Less" else "More",
                color = color,
                textAlign = TextAlign.End,
                onClick =  { expanded = !expanded }
            )
        }
    }
}

@Composable
fun ExpandableMessageTextButton(
    modifier: Modifier = Modifier,
    buttonState: String,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick() }
    ) {
        Text(
            text = buttonState,
            fontSize = fontSize,
            fontWeight = fontWeight,
            color = color,
            textAlign = textAlign,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun PreviewExpandableMessageText() {
    ExpandingTextMessage(
        modifier = Modifier.padding(8.dp),
        message = "This is a message for testing Text Composable. This is a message for testing Text Composable. This is a message for testing Text Composable. This is a message for testing Text Composable. This is a message for testing Text Composable. This is a message for testing Text Composable.This is a message for testing Text Composable",
        fontSize = 18.sp,
        color = Color.Black,
        textStyle = MaterialTheme.typography.bodyLarge,
        defaultMaxLines = 3
    )
}