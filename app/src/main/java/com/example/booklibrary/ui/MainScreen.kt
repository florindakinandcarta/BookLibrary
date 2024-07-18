package com.example.booklibrary.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.booklibrary.navigation.Navigation
import com.example.booklibrary.navigation.NavigationHost
import com.example.booklibrary.ui.home.BottomNavigation

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (shouldShowBottomNav(currentRoute)) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        NavigationHost(navController, modifier = Modifier.padding(innerPadding))
    }
}

private fun shouldShowBottomNav(currentRoute: String?): Boolean {
    return when (currentRoute) {
        Navigation.Login.route,
        Navigation.Register.route,
        Navigation.ForgotPassword.route,
        Navigation.SearchWithGoogle.route -> false
        else -> true
    }
}