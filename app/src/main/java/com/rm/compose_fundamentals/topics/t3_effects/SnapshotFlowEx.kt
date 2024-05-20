package com.rm.compose_fundamentals.topics.t3_effects

import android.util.Log
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Preview
@Composable
fun PreviewSnapshotFlow() {
    SnapshotFlowEx()
}

// collectAsState converts flow -> State
// snapShotFlow converts State -> flow
@Composable
fun SnapshotFlowEx() {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { scaffoldState.snackbarHostState }
            .distinctUntilChanged()
            .collect {
                Log.d("state", "A snackbar with message $it was shown")
            }
    }
}
