package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.ui.home.HomeScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = BottomTab.Home.route
    ) {
        composable(route = BottomTab.Home.route) {
            HomeScreen(
                onSearchClick = {},
                onClickedBook = {},
                onNotificationClick = {}
            )
        }
        composable(route = BottomTab.Requested.route) {
            navHostController.navigate(Graph.REQUESTED)
        }
        composable(route = BottomTab.Borrowed.route) {
            navHostController.navigate(Graph.BORROWED)
        }
        composable(route = BottomTab.Profile.route) {
            navHostController.navigate(Graph.PROFILE)
        }
        requestedGraph(navHostController = navHostController)
        borrowedGraph(navHostController = navHostController)
        profileGraph(navHostController = navHostController)
    }
}