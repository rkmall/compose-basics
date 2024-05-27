package com.rm.compose_fundamentals.topics.t4_managing_states.remember

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun PreviewCompositionOrderWarning() {
    val viewModel: ClickViewModel = viewModel()
    val textState by viewModel.text.collectAsState() // Internal state

    CompositionOrderWarning.ComposableOrderWarning(
        text = textState,
        onClick = { viewModel.updateText() }
    )
}

private object CompositionOrderWarning {
    @Composable
    fun ComposableOrderWarning(
        text: String,
        onClick: () -> Unit
    ) {
        /**
         * Parameter "text" only serves as initial value for state "name"
         * inside the mutableStateOf().
         *
         * Parent composable above observe the state "textState" and passes to
         * this Composable, it doesn't have any effect because state "name" only uses
         * parameter "text" to set initial value.
         */
        //val name by remember { mutableStateOf(text) } // This doesn't work

        /**
         * To update state "name" when parameter "text" changes, use the parameter as
         * the key for remember function.
         */
        val name by remember(text) { mutableStateOf(text) } // use remember with key

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 30.sp
            )

            Button(onClick = { onClick() }) {
                Text(text = "Update Text")
            }
        }
    }
}

@Preview
@Composable
fun PreviewComposeRecomposition1() {
    ComposeRecomposition1.ParentComposable()
}

private object ComposeRecomposition1 {
    @Composable
    fun ParentComposable() {
        Log.d("state", "ParentComposable called" )
        var clicks by remember { mutableIntStateOf(0) } // Internal state

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            ClickCounter(
                clicks = clicks,       // passing state
                onClick = { clicks++ } // EventHandler (in this case changes state, re-invokes ClickCounter)
            )
        }
    }

    @Composable
    fun ClickCounter(
        clicks: Int, // UI state, when this state changes, this composable will be re-invoked
        onClick: () -> Unit // lambda to pass onClick event to EventHandler up the hierarchy
    ) {
        Log.d("state", "ClickCounter called" )
        Button(onClick = { onClick() }) {
            Text(
                text = "I have been clicked $clicks times",
                fontSize = 24.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewComposeRecomposition2() {
    val viewModel: ClickViewModel = viewModel()
    ComposeRecomposition2.ParentComposable(viewModel)
}

object ComposeRecomposition2 {
    @Composable
    fun ParentComposable(
        viewModel: ClickViewModel
    ) {
        // Internal states
        val scope = rememberCoroutineScope()
        val clicks by viewModel.clicks.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            ClickCounter(
                clicks = clicks,
                onClick = {
                    scope.launch {
                        viewModel.updateClick()
                    }
                }
            )
        }
    }

    @Composable
    fun ClickCounter(
        clicks: Int,
        onClick: () -> Unit
    ) {
        Log.d("state", "ClickCounter called" )
        Button(onClick = { onClick() }) {
            Text(
                text = "I have been clicked $clicks times",
                fontSize = 24.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewComposeIntelligentRecomposition() {
    val viewModel: ClickViewModel = viewModel()
    ComposeIntelligentRecomposition.ParentComposable(viewModel = viewModel)
}

object ComposeIntelligentRecomposition {
    @Composable
    fun ParentComposable(
        viewModel: ClickViewModel // this parameter remains same
    ) {
        // No internal states

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            ClickCounter(
                clicks = viewModel.clicks.collectAsState().value, // state directly observed in parameter
                onClick = { viewModel.incrementClick() }
            )
        }
    }

    @Composable
    fun ClickCounter(
        clicks: Int,
        onClick: () -> Unit
    ) {
        Log.d("state", "ClickCounter called")
        Button(onClick = { onClick() }) {
            Text(
                text = "I have been clicked $clicks times",
                fontSize = 24.sp
            )
        }
    }
}
