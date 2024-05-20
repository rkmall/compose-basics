package com.rm.compose_fundamentals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.rm.compose_fundamentals.topics.t3_effects.launchedeffect.LaunchEffectIntro
import com.rm.compose_fundamentals.topics.t3_effects.launchedeffect.LaunchedEffectTest
import com.rm.compose_fundamentals.topics.t3_effects.launchedeffect.MyState
import com.rm.compose_fundamentals.topics.t8_mvi.favorite.FavoriteViewModel
import com.rm.compose_fundamentals.topics.t8_mvi.ui.StartView
import com.rm.compose_fundamentals.ui.theme.ComposefundamentalsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposefundamentalsTheme {
                //CupcakeApp()
                //GetTextScreen()
                //TextFieldDemo()

                /*StartView(
                    viewModel = viewModel,
                    onFavorite = {
                        if (viewModel.state.favorite) {
                            viewModel.onUnFavorite()
                        } else {
                            viewModel.onFavorite()
                        }
                    }
                )*/
            }
        }
    }
}