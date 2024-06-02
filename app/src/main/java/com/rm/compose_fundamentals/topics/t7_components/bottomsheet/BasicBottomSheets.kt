package com.rm.compose_fundamentals.topics.t7_components.bottomsheet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Preview
@Composable
fun BottomSheetsPreview() {
    ModalBottomSheetExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ModalBottomSheetExample() {
    /**
     * Variable to track expand/collapse sheet state.
     * It provides access to various functions and properties related to the current sheet state.
     * However the sheetState functions are suspend functions therefore, CoroutineScope
     * is required to invoke sheetState functions.
     */
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(
                    imageVector = Icons.Filled.Add ,
                    contentDescription = "Show BottomSheet"
                )
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = "Show BottomSheet")
            }
        }
    ) {
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }, // for clicks outside the bottom sheet
                sheetState = sheetState,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "Bottom Sheet",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp, horizontal = 38.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }
                ) {
                    Text(text = "Hide Bottom Sheet")
                }
            }
        }
    }
}