package com.example.booklibrary.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.booklibrary.ui.userProfile.ProfileScreen

fun NavGraphBuilder.profileGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreen.Profile.route
    ){
        composable(ProfileScreen.Profile.route){
            ProfileScreen(
                onSettingsClicked = {
                    navHostController.navigate(ProfileScreen.Settings.route)
                },
                onAllUsersClicked = {
                    navHostController.navigate(ProfileScreen.AllUsers.route)
                },
                onChangeProfilePhotoClicked = {
                    navHostController.navigate(ProfileScreen.ChangePicture.route)
                }
            )
        }
        composable(ProfileScreen.ChangePicture.route){
            //the screen where we open the camera
        }
        composable(ProfileScreen.Settings.route){
            //settings screen to log out and the darkmode option
        }
        composable(ProfileScreen.AllUsers.route){
            // All users screen
        }
    }
}

sealed class ProfileScreen(val route:String){
    object Profile: ProfileScreen("PROFILE")
    object ChangePicture: ProfileScreen("CAMERA")
    object Settings: ProfileScreen("SETTINGS")
    object AllUsers: ProfileScreen("ALLUSERS")
}