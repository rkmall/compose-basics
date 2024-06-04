package com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.usingbottomappbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Home
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Notification
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Post
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Profile
import com.rm.compose_fundamentals.topics.t7_components.navigation.bottomnavigation.screen.Settings


@Preview
@Composable
fun PreviewBottomNavigationExample() {
    CustomBottomNavigation()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomBottomNavigation() {
    val navController = rememberNavController()
    var selectedNavIcon by remember { mutableStateOf(Icons.Default.Home) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedNavIcon = Icons.Default.Home
                        navController.navigate(BottomNavRoute.Home.route) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        tint = if (selectedNavIcon == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedNavIcon = Icons.Default.Notifications
                        navController.navigate(BottomNavRoute.Notification.route) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = if (selectedNavIcon == Icons.Default.Notifications) Color.White else Color.DarkGray
                    )
                }

                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(onClick = {
                        selectedNavIcon = Icons.Default.Add
                        navController.navigate(BottomNavRoute.Post.route) {
                            popUpTo(0)
                        }
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = if (selectedNavIcon == Icons.Default.Add) Color.White else Color.DarkGray
                        )
                    }
                }

                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        selectedNavIcon = Icons.Default.Person
                        navController.navigate(BottomNavRoute.Profile.route) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = if (selectedNavIcon == Icons.Default.Person) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        selectedNavIcon = Icons.Default.Settings
                        navController.navigate(BottomNavRoute.Settings.route) {
                            popUpTo(0)
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = if (selectedNavIcon == Icons.Default.Settings) Color.White else Color.DarkGray
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = BottomNavRoute.Home.route
        ) {
            composable(BottomNavRoute.Home.route) { Home() }
            composable(BottomNavRoute.Notification.route) { Notification() }
            composable(BottomNavRoute.Post.route) { Post() }
            composable(BottomNavRoute.Profile.route) { Profile() }
            composable(BottomNavRoute.Settings.route) { Settings() }
        }
    }
}
