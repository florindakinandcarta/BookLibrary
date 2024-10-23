package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.authGraph(navHostController: NavHostController) {
    navigation(startDestination = "login", route = "auth_graph"){
        composable("login"){
            //the login screen
        }
        //other composables that we navigate from login screen
    }
}