package com.rm.compose_fundamentals.topics.t6_navigation.intro

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument

private object NavControllerAndNavHost {
    @Composable
    fun MyApp() {
        /**
         * NavController should be placed in the top level Composable hierarchy so that
         * it is accessible to all the children Composables down the hierarchy.
         */
        val navController = rememberNavController()

        /**
         * NavHost takes NavController and startDestination route.
         * NavHost is used to create NavGraph. Under the hood, NavHost calls
         * NavController.createGraph using startDestination and NavGraphBuilder scope and returns NavGraph.
         * The calls to NavGraphBuilder.composable() add destinations to NavGraph.
         */
        NavHost(
            navController =  navController,
            startDestination = "profile"
        ) {
            composable(
                route = "profile/{userId}",
                content = {
                    Profile { navController.navigate(route = "settings") }
                },
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            )

            composable(
                route = "friendList",
                content = {
                    Settings { navController.navigate(route = "profile") }
                },
            )
        }
    }
}

@Composable
fun Profile(onNavigateToSettings: () -> Unit) {
    Column {
        Text("Profile")
        Button(onClick = { onNavigateToSettings() }) {
            Text("Go to Settings")
        }
    }
}

@Composable
fun Settings(onNavigateToProfile: () -> Unit) {
    Column {
        Text("Settings")
        Button(onClick = { onNavigateToProfile() }) {
            Text("Go to Profile")
        }
    }
}

private object AlternativeWayToCreateNavGraph {
    @Composable
    fun MyApp() {
        val navController = rememberNavController()

        val navGraph = remember(navController) {
            navController.createGraph(startDestination = "profile") {
                composable(
                    route = "profile",
                    content = { Profile { navController.navigate(route = "friendList") } }
                )

                composable(
                    route = "friendList",
                    content = { Settings { navController.navigate(route = "profile") } }
                )
            }
        }
        NavHost(navController, navGraph )
    }
}
