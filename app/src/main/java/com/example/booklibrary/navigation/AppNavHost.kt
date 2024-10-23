package com.example.booklibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.ui.home.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {
    var isSplashScreenFinished by rememberSaveable {
        mutableStateOf(false)
    }
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = if (isSplashScreenFinished) {
            NavigationItem.Home.route
        } else {
            NavigationItem.Splash.route
        }
    ) {
        composable(NavigationItem.Splash.route) {
            val userJWTToken = userViewModel.userJWTToken.collectAsState()
//            if (userJWTToken.)
            //SplashScreen {
            // navcontroller.navigate(NavigationItem.Home.route)
            // isSplashScreenFinished = true // maybe here we can observe the viewmodel
            // if the user is logged in make it true
            // }
        }
        composable(NavigationItem.Home.route) {
            HomeScreen(onNotificationClick = { /*TODO*/ }, onSearchClick = { /*TODO*/ }) {

            }
        }
    }
}