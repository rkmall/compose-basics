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


// Define the MyApp composable, including the `NavController` and `NavHost`.
private object NavControllerAndNavHost {
    @Composable
    fun MyApp() {
        // Create NavController
        val navController = rememberNavController()

        // Create NavHost
        NavHost(
            navController =  navController,
            startDestination = "profile"
        ) {
            composable(
                route = "profile/{userId}",
                content = { ProfileScreen { navController.navigate(route = "friendList") } },
                arguments = listOf(navArgument("userId") {type = NavType.StringType})
            )

            composable(
                route = "friendList",
                content = { FriendListScreen { navController.navigate(route = "profile") } },
            )
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
                    content = { ProfileScreen { navController.navigate(route = "friendList") } }
                )

                composable(
                    route = "friendList",
                    content = { FriendListScreen { navController.navigate(route = "profile") } }
                )
            }
        }
        NavHost(navController, navGraph )
    }
}

// Define the Profile composable.
@Composable
fun ProfileScreen(onNavigateToFriendsList: () -> Unit) {
    Column {
        Text("Profile")
        Button(onClick = { onNavigateToFriendsList() }) {
            Text("Go to Friends List")
        }
    }
}

// Define the FriendsList composable.
@Composable
fun FriendListScreen(onNavigateToProfile: () -> Unit) {
    Column {
        Text("Friends List")
        Button(onClick = { onNavigateToProfile() }) {
            Text("Go to Profile")
        }
    }
}