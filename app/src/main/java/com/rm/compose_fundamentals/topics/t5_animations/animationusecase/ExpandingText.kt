package com.rm.compose_fundamentals.topics.t5_animations.animationusecase

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.topics.t7_components.navigation.tablayout.TopBar

@Composable
fun ExpandingTextScreen(
    title: String,
    description: String
) {
    Scaffold(
        topBar = { TopBar("Expanding Text example") },
        containerColor = Color(0xFFFFF9C4)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(vertical = 4.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ContentUsingExpandingText(
                modifier = Modifier,
                title = title,
                description = description
            )
            ContentUsingExpandingText(
                modifier = Modifier,
                title = title,
                description = description
            )
            ContentUsingExpandingText(
                modifier = Modifier,
                title = title,
                description = description
            )
            ContentUsingExpandingText(
                modifier = Modifier,
                title = title,
                description = description
            )
        }
    }
}

@Preview
@Composable
private fun PreviewExpandingTextScreen() {
    ExpandingTextScreen(
        title = "Title",
        description = "Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing."
    )
}

@Composable
fun ContentUsingExpandingText(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Surface(
        modifier = modifier
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.large,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
            )

            ExpandingText(description = description)

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {}) {
                    Text(text = "KOTLIN")
                }
                Button(onClick = {}) {
                    Text(text = "COMPOSE")
                }
                Button(onClick = {}) {
                    Text(text = "UI")
                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewContentUsingExpandingText() {
    ContentUsingExpandingText(
        title = "Title",
        description = "Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing."
    )
}



@Composable
fun ExpandingText(
    modifier: Modifier = Modifier,
    description: String,
    color: Color = Color.Unspecified,
    fonSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .clickable { expanded = !expanded },
            text = description,
            color = color,
            fontSize = fonSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (expanded) Int.MAX_VALUE else 3
        )
    }
}

@Preview
@Composable
private fun PreviewExpandingText() {
    ExpandingText(description = "Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing. Some text description for testing.")
}
