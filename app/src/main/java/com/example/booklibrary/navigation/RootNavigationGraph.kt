package com.example.booklibrary.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.booklibrary.ui.home.MyBottomTabsBar
import com.example.booklibrary.util.getUserJWTToken

@Composable
fun RootNavigationGraph(navHostController: NavHostController, dataStore: DataStore<Preferences>) {
    val jwtTokenFlow = getUserJWTToken(dataStore)
    val jwtToken by jwtTokenFlow.collectAsState(initial = "")
    Scaffold(bottomBar = {
        if (!jwtToken.isNullOrEmpty()) {
            MyBottomTabsBar(
                navHostController = navHostController
            )
        }
    }) {
        it
        NavHost(
            navController = navHostController,
            route = Graph.ROOT,
            startDestination = if (jwtToken.isNullOrEmpty()) Graph.AUTHENTICATION else Graph.HOME
        ) {
            authGraph(navHostController = navHostController, dataStore = dataStore)
            navigation(route = Graph.HOME, startDestination = BottomTab.Home.route) {
                homeNavGraph(navHostController = navHostController)
            }
            navigation(route = Graph.REQUESTED, startDestination = BottomTab.Requested.route) {
                requestedGraph(navHostController = navHostController)
            }
            navigation(route = Graph.BORROWED, startDestination = BottomTab.Borrowed.route) {
                borrowedGraph(navHostController = navHostController)

            }
            navigation(route = Graph.PROFILE, startDestination = BottomTab.Profile.route) {
                profileGraph(navHostController = navHostController)
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
    const val REQUESTED = "requested_graph"
    const val BORROWED = "borrowed_graph"
    const val PROFILE = "profile_graph"
}