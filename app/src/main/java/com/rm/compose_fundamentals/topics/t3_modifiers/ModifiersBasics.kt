package com.rm.compose_fundamentals.topics.t3_modifiers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R
import com.rm.compose_fundamentals.topics.t2_layouts.Artist
import com.rm.compose_fundamentals.topics.t2_layouts.ArtistCardWithImage
import com.rm.compose_fundamentals.ui.theme.md_theme_dark_onBackground

@Preview
@Composable
fun PreviewPadding() {
    //PaddingModifierBefore()
    PaddingModifierAfter()
}

@Composable
fun PaddingModifierBefore() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),
        text = "Hello There",
        fontSize = 28.sp
    )
}

@Composable
fun PaddingModifierAfter() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .padding(50.dp),
        text = "Hello There",
        fontSize = 28.sp
    )
}

@Preview
@Composable
fun PreviewPaddingMultiple() {
    PaddingMultipleModifier()
}

@Composable
fun PaddingMultipleModifier() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .padding(40.dp)
            .background(Color.Yellow)
            .padding(20.dp)
            .background(Color.Cyan)
            .width(200.dp)
            .height(200.dp)
    ) {
        Text(text = "Hello")
        Text(text = "There")
    }
}

@Preview
@Composable
fun PreviewSize() {
    SizeModifier()
}

@Composable
fun SizeModifier(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.Green)
            .size(width = 200.dp, height = 200.dp)
    ) {
        Text(text = "Hello")
        Text(text = "There")
    }
}


@Preview
@Composable
fun PreviewMaxSizeRequired() {
    RequiredSizeModifier()
}

@Composable
fun RequiredSizeModifier(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Title",
            modifier = Modifier
                .requiredSize(100.dp)   // specify fixed size for children
                .background(Color.Green)
        )

        Text(text = "Description",
            modifier = Modifier
                .requiredSize(150.dp, 100.dp)
                .background(Color.Cyan)
        )
    }
}

@Preview
@Composable
fun PreviewOffsetModifier() {
    OffsetModifier()
}

@Composable
fun OffsetModifier() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Yellow)
    ) {
        Text(
            modifier = Modifier
                .offset(50.dp, 50.dp), // offsets element like margin does but doesn't push other elements
            text = "Hello",            // offset always starts from top-left corner of the element current position
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "There",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewBorderModifier() {
   BorderModifier()
}

@Composable
fun BorderModifier() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Yellow)
            .border(BorderStroke(8.dp, Color.Black))
            .padding(40.dp)
            .background(Color.Cyan)
            .border(BorderStroke(8.dp, Color.Black))
    ) {
        Text(
            modifier = Modifier
                .padding(30.dp)
                .border(5.dp, Color.Yellow)
                .padding(20.dp)
                .offset(50.dp, 50.dp)
                .border(4.dp, Color.DarkGray)
                .padding(20.dp),
            text = "Hello",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Preview
@Composable
fun PreviewClickableModifier() {
    ClickableModifier()
}

@Composable
fun ClickableModifier() {
    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Yellow),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var num by remember { mutableIntStateOf(0) }

        Text(
            modifier = Modifier
                .padding(bottom = 30.dp),
            text = "$num",
            fontSize = 30.sp
        )

        Text(
            modifier = Modifier
                .clickable {
                    num++
                }
                .background(Color.Cyan)
                .border(1.dp, Color.Black)
                .padding(8.dp),
            text = "Click Me",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewBaseLineModifier() {
    PaddingBaseLine()
}

@Composable
fun PaddingBaseLine(modifier: Modifier = Modifier) {
    Column(
        Modifier.background(Color.Cyan)
    ) {
        Text(text = "Title", modifier.paddingFromBaseline(100.dp))
        Text(text = "Description", modifier.paddingFromBaseline(50.dp, 50.dp))
    }
}

@Preview
@Composable
fun PreviewWeightModifierRow() {
    WeightModifierRow()
}

@Composable
fun WeightModifierRow(modifier: Modifier = Modifier) {
    Column(
    ) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Green)
                .weight(3f),
        ) {
            Text(text = "Row 1")
        }
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .weight(1f)
        ) {
            Text(text = "Row 2")
        }
    }
}


@Preview
@Composable
fun PreviewWeightModifierColumn() {
    WeightModifierColumn()
}

@Composable
fun WeightModifierColumn(modifier: Modifier = Modifier) {
    Row(
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Green)
                .weight(2f),
        ) {
            Text(text = "Row 1")
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Yellow)
                .weight(1f)
        ) {
            Text(text = "Row 2")
        }
    }
}

@Preview
@Composable
fun PreviewMatchParentModifier() {
    MatchParentSizeBox()
}

@Composable
fun MatchParentSizeBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Green)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = modifier
            .matchParentSize()
            .background(Color.Yellow)
        )
    }
}

@Preview
@Composable
fun PreviewModifierOrder() {
    MyCard1(artist = Artist(R.drawable.logo, "Avatar", "10:10"))
}

@Composable
fun MyCard1(artist: Artist, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable {}   // clickable is before padding
            .padding(30.dp) // so padding are clickable too
            .background(md_theme_dark_onBackground)
    ) {
        ArtistCardWithImage(artist = artist)
    }
}

@Composable
fun MyCard2(artist: Artist, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(30.dp)
            .clickable {} // padding part is not clickable
            .background(md_theme_dark_onBackground)
    ) {
        ArtistCardWithImage(artist = artist)
    }
}
