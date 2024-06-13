package com.rm.compose_fundamentals.topics.t2_layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnLayoutBasicSetup() {
    Column(
        modifier = Modifier
            .fillMaxWidth() // fill entire available width
            .padding(vertical = 8.dp, horizontal = 16.dp), // padding around the whole column
        verticalArrangement = Arrangement.spacedBy(12.dp), // spacing between each items
        horizontalAlignment = Alignment.CenterHorizontally // align center in horizontal axis
    ) {
        ColumnContent(
            contentTitle = "Hello" ,
            contentDescription = "Hello there description. Hello there description."
        )
        ColumnContent(
            contentTitle = "Hallo" ,
            contentDescription = "Hallo there description. Hallo there description."
        )
        ColumnContent(
            contentTitle = " Hola" ,
            contentDescription = "Hola there description. Hola there description."
        )
        ColumnContent(
            contentTitle = "Bonjour" ,
            contentDescription = "Bonjour there description. Bonjour there description."
        )
    }
}

@Preview
@Composable
private fun PreviewColumnLayoutBasicSetup() {
    ColumnLayoutBasicSetup()
}

@Composable
fun ColumnContent(
    modifier: Modifier = Modifier,
    contentTitle: String,
    contentDescription: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = contentTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = contentDescription,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(8.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {},
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "KOTLIN")
            }
            Button(
                onClick = {},
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "COMPOSE")
            }
        }
    }
}

@Preview
@Composable
private fun PreviewColumnContent() {
    ColumnContent(
        modifier = Modifier.padding(16.dp),
        contentTitle = "My Title",
        contentDescription = "Some description for testing, some description for testing."
    )
}

@Composable
fun ColumnArrangementTop() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementTop() {
    ColumnArrangementTop()
}

@Composable
fun ColumnArrangementCenter() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementCenter() {
    ColumnArrangementCenter()
}

@Composable
fun ColumnArrangementBottom() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementBottom() {
    ColumnArrangementBottom()
}

@Composable
fun ColumnArrangementSpaceBetween() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementSpaceBetween() {
    ColumnArrangementSpaceBetween()
}

@Composable
fun ColumnArrangementSpaceEvenly() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementSpaceEvenly() {
    ColumnArrangementSpaceEvenly()
}

@Composable
fun ColumnArrangementSpaceAround() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnArrangementSpaceAround() {
    ColumnArrangementSpaceAround()
}

@Composable
fun ColumnAlignmentStart() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnAlignmentStart() {
    ColumnAlignmentStart()
}

@Composable
fun ColumnAlignmentCenterHorizontally() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnAlignmentCenterHorizontally() {
    ColumnAlignmentCenterHorizontally()
}

@Composable
fun ColumnAlignmentEnd() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        ColumnText(text = "Hello")
        ColumnText(text = "World")
        ColumnText(text = "Compose")
    }
}

@Preview
@Composable
private fun PreviewColumnAlignmentEnd() {
    ColumnAlignmentEnd()
}

@Composable
fun ColumnText(text: String) {
    Text(
        modifier = Modifier
            .background(Color.Green),
        text = text,
        fontSize = 35.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}
