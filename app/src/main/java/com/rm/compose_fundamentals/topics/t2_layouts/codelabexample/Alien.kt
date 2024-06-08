package com.rm.compose_fundamentals.topics.t2_layouts.codelabexample

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rm.compose_fundamentals.R
import kotlin.random.Random


@Composable
fun AndroidAliensGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(5)
    ) {
        items(50) {
            AndroidAlien(
                color = getRandomRGB(),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

fun getRandomRGB(): Color {
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val random = Random.nextInt(0, 3)
    return colors[random]
}

@Composable
fun AndroidAliensWithInfo() {
    Scaffold(
        topBar = { TopInfoBar(score = 100, lives = 3 ) },
        bottomBar = { BottomInfoBar() }
    ) {
        AndroidAliensRowAlign(Modifier.padding(it))
    }
}

@Composable
fun TopInfoBar(
    score: Long,
    lives: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "SCORE: $score",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "LIVES: $lives",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun BottomInfoBar() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.7f)),
        text = "PRESS START",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun AndroidAliensBox() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AndroidAliensRowWeight()
        Spacer(
            modifier = Modifier
                // Match child element ti match already defined size of the containing box
                .matchParentSize() // doesn't impact Box's final size
                .fillMaxSize() // does impact Box's final size
                .background(Color.Gray.copy(alpha = 0.7f))
        )
        Text(
            text = "GAME OVER",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun AndroidAliensRowWeight() {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .size(70.dp)  // takes exactly 70dp
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Yellow,
            // Composable with weight modifier takes rest of the space after the composables
            // that have no weight modifier
            modifier = Modifier
                .weight(1f)   // takes rest of the space
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .size(70.dp)   // takes exactly 70dp
                .padding(4.dp)
        )
    }
}

@Composable
fun AndroidAliensRowAlign(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Red,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Blue,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
                // override the rule enforced by parent layout
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun AndroidAliensColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidAlien(
            color = Color.Red,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
        )
        AndroidAlien(
            color = Color.Green,
            modifier = Modifier
                .size(70.dp)
                .padding(4.dp)
        )
    }
}


@Composable
fun AndroidAlien(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.alien), 
        contentDescription = null ,
        modifier = modifier,
        colorFilter = ColorFilter.tint(color)
    )
}

@Preview
@Composable
private fun PreviewAndroidAliensGrid() {
    AndroidAliensGrid()
}

@Preview
@Composable
private fun PreviewBottomInfoBar() {
    BottomInfoBar()
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun PreviewAndroidAlienWithInfo() {
    AndroidAliensWithInfo()
}

@Preview
@Composable
private fun PreviewAndroidAliensBox() {
    AndroidAliensBox()
}

@Preview
@Composable
private fun PreviewAndroidAliensRow() {
    //AndroidAliensRowAlign()
    AndroidAliensRowWeight()
}

@Preview
@Composable
private fun PreviewAndroidAliensColumn() {
    AndroidAliensColumn()
}

@Preview
@Composable
private fun PreviewAndroidAlien() {
    AndroidAlien(color = Color.Green)
}