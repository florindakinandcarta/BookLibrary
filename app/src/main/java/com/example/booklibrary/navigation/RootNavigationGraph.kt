package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.util.getUserJWTToken

@Composable
fun RootNavigationGraph(navHostController: NavHostController, dataStore: DataStore<Preferences>) {
    val jwtTokenFlow = getUserJWTToken(dataStore)
    val jwtToken by jwtTokenFlow.collectAsState(initial = "")
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = if (jwtToken.isNullOrEmpty()) Graph.AUTHENTICATION else Graph.HOME
    ) {
        authGraph(navHostController = navHostController, dataStore = dataStore)
        composable(route = Graph.HOME) {
            MainScreen()
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