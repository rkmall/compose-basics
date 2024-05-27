package com.rm.compose_fundamentals.topics.t5_effects.launchedeffect.launchedeffectviewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

val viewModel = LaunchedEffectViewModel()

@Preview
@Composable
fun PreviewLaunchedEffectWithViewModel() {
    val scope = rememberCoroutineScope()

    // ViewModel methods should be always called inside LaunchedEffect or CoroutineScope
    LaunchedEffectWithViewModel(viewModel = viewModel) { event ->
        scope.launch {
            viewModel.setEvent(event) // send event to viewmodel
        }
    }
}

@Composable
fun LaunchedEffectWithViewModel(
    viewModel: LaunchedEffectViewModel,
    onEventSent: (LaunchedEffectViewModel.ScreenEvents) -> Unit
) {
    Log.d("launch", "Composition entered...")

    var text by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }

    /**
     * States outside Composable (in this case, StateFlow hosted in ViewModel) can be directly
     * inside Composable as Compose State<T> with lifecycle
     */
    val viewModelText by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    
    /**
     * LaunchedEffect is invoked when this Composable enter the composition.
     * Then, LaunchedEffect launches coroutine block in the Composition CoroutineContext.
     * This coroutine remains active through out the Composition and
     * will be cancelled when the LaunchedEffect leaves the Composition.
     */
    LaunchedEffect(key1 = Unit) {
        Log.d("launch", "LaunchedEffect called")

        viewModel.effect.collect { effect -> // observe effect if any
            when(effect) {
                is LaunchedEffectViewModel.ScreenEffects.ShowSnackBar -> {
                    Log.d("launch", "Displaying snackBar")
                    snackBarHostState.showSnackbar(
                        message = effect.message,
                        actionLabel = "OK",
                        duration = SnackbarDuration.Short
                    )
                }

                is LaunchedEffectViewModel.ScreenEffects.Navigate -> {
                    Toast.makeText(context, effect.route, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = viewModelText,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text(text = "Input some text") }
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Send event up the UI tree
            Button(onClick = { onEventSent(LaunchedEffectViewModel.ScreenEvents.OnUpdateText(text)) }) {
                Text(text = "Update Text")
            }
            Spacer(modifier = Modifier.height(40.dp))

            // Send event up the UI tree
            Button(onClick = { onEventSent(LaunchedEffectViewModel.ScreenEvents.OnShowSnackBarButtonClicked) }) {
                Text(text = "Show SnackBar")
            }
            Spacer(modifier = Modifier.height(40.dp))

            // Send event up the UI tree
            Button(onClick = { onEventSent(LaunchedEffectViewModel.ScreenEvents.OnNavigateButtonClicked) }) {
                Text(text = "Navigate")
            }
        }
    }
}