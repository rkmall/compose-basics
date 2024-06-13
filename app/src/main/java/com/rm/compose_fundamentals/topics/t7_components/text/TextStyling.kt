package com.rm.compose_fundamentals.topics.t7_components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R
import com.rm.compose_fundamentals.topics.t5_animations.animationusecase.ExpandingTextMessage
import com.rm.compose_fundamentals.topics.t7_components.card.MessageCard

@Composable
fun TextOverflowExample(message: String) {
    MessageCard {
        ExpandingTextMessage(
            modifier = Modifier.padding(8.dp),
            message = message,
            color = Color.White
        )
    }
}

@Composable
fun TextStyling() {
    val fontFamily: FontFamily = remember { fontFamily() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("H") // apply above custom style
                }
                append("ello ") // apply default style described at the bottom

                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp
                    )
                ) {
                    append("W") // apply above custom style
                }
                append("orld") // apply default style described at the bottom
            },
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }

}

// When importing fonts, make sure they are in lowercase with underscore instead of hyphen
fun fontFamily(): FontFamily {
    return FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold)
    )
}


@Preview
@Composable
private fun PreviewTextStylingSimple() {
    TextOverflowExample("Hello there, how are you? Just checking the text overflow and \"More\" button to show when there is more than three lines. Some random text, some random text, some random text, some random text.")
}

@Preview
@Composable
fun PreviewTextStyling() {
    TextStyling()
}

