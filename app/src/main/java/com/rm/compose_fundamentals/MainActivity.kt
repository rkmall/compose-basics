package com.rm.compose_fundamentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.CupcakeApp
import com.rm.compose_fundamentals.topics.t6_navigation.cupcake_example.CupcakeScreen
import com.rm.compose_fundamentals.topics.t8_mvi.SearchTextField
import com.rm.compose_fundamentals.topics.t8_mvi.SearchViewModel
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //val searchViewModel by viewModels<SearchViewModel>()

        setContent {
            ComposefundamentalsTheme(dynamicColor = false) {
                //WindowInsetsEdgeToEdge()
                //WindowInsetsSystemBarsAsPadding()
                //WindowInsetsStatusBarsAsPadding()
                //WindowInsetsNavigationBarsAsPadding()

                //WindowInsetsSize()

                //HandleIMEInsetPaddingInChild()
                //HandleIMEInsetPaddingUpTheHierarchy()

                //ConsumeInset()

                //SearchTextField(viewModel = searchViewModel)

                CupcakeApp()
            }
        }
    }
}

@Composable
fun WindowInsetsEdgeToEdge() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    )
}

@Composable
fun WindowInsetsSystemBarsAsPadding() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        )
    }
}


@Composable
fun WindowInsetsStatusBarsAsPadding() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        )
    }
}


@Composable
fun WindowInsetsNavigationBarsAsPadding() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        )
    }
}

@Composable
fun WindowInsetsSize() {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .windowInsetsTopHeight(WindowInsets.statusBars)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .weight(1f)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .windowInsetsBottomHeight(WindowInsets.navigationBars)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HandleIMEInsetPaddingInChild() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // apply systemBars as padding
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .imeNestedScroll() // display ime on scroll

        ) {
            items(50) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Unspecified)
                        .border(1.dp, Color.Black)
                        .padding(14.dp),
                    text = "Item $it",
                    fontSize = 20.sp
                )
            }
        }

        var textFieldVal by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding(), // handle ime inset padding
            value = textFieldVal,
            onValueChange = { textFieldVal = it  },
            placeholder = { Text(text = "Type something") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.secondary
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HandleIMEInsetPaddingUpTheHierarchy() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding() // apply systemBars as padding
            .imePadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .imeNestedScroll() // display ime on scroll

        ) {
            items(50) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Unspecified)
                        .border(1.dp, Color.Black)
                        .padding(14.dp),
                    text = "Item $it",
                    fontSize = 20.sp
                )
            }
        }

        var textFieldVal by remember { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textFieldVal,
            onValueChange = { textFieldVal = it  },
            placeholder = { Text(text = "Type something") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.secondary
            )
        )
    }
}

@Composable
fun ConsumeInset() {
    Scaffold { innerPadding ->
        // innerPadding contains inset information to use and apply
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .padding(innerPadding)
                //.consumeWindowInsets(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red)
                    //.windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.DarkGray)
                )
            }
        }
    }
}

