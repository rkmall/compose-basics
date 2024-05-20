package com.rm.compose_fundamentals.topics.t4_managing_states

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview
@Composable
fun PreviewRemember1() {
    RememberTest()
}

@Preview
@Composable
fun PreviewRemember2() {
    ColorBox()
}

@Preview
@Composable
fun PreviewRemember3() {
    ColorBoxExternalState()
}


@Preview
@Composable
fun PreviewRemember4() {
    ColorBoxWithoutRemember()
}

@Preview
@Composable
fun PreviewRemember5() {
    ObservedVsNonObserved()
}

@Composable
fun RememberTest() {
    // remember saves the 'text' value and returns the same value until explicitly changed
    // mutableStateOf makes the value observable and mutable
    var text by remember { mutableStateOf("Hello") } // INTERNAL STATE

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.size(30.dp))

        val onClick: () -> Unit = { text = "Bye" }
        Button(onClick = onClick) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun ColorBox() {
    var color by remember { mutableStateOf(Color.Cyan) } // INTERNAL STATE
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .clickable {
                color = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
    )
}

@Composable
fun Box1(
    modifier: Modifier,
    changeColor: (Color) -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.Yellow)
            .clickable {
                val color = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
                changeColor(color)
            }
    )
}

@Composable
fun ColorBoxExternalState() {
    var color by remember { mutableStateOf(Color.Cyan) }    // external state

    Column {
        Box1(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color = it
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(color)  // this state is controlled by Box1
        )
    }
}







@SuppressLint("UnrememberedMutableState")
@Composable
fun ColorBoxWithoutRemember() {
    // Without remember, everytime the color value changes, this function is called
    // and on this first line the color is set to Cyan. So no color change is observed.
    val color = mutableStateOf(Color.Cyan)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            }
    )
}

@Composable
fun ObservedVsNonObserved() {
    // Not observed by composable
    var notObserved = remember { 1 }

    // MutableState type and compose will observed any changes to it.
    val observed = remember { mutableIntStateOf(10) }

    Column {
        Text(text = "$notObserved")
        Text(text = "${observed.intValue}")
        Button(
            onClick = { observed.intValue++ },
        ) {
            Text(text = "Click me")
        }
    }
}
