package com.example.booklibrary.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.ui.userProfile.ProfileScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.profileGraph(navHostController: NavHostController) {
    composable(ProfileScreen.Profile.route) {
        val userViewModel: UserViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                userViewModel.getUserInfo()
            }
        }
        ProfileScreen(
            onSettingsClicked = {
                navHostController.navigate(ProfileScreen.Settings.route)
            },
            onAllUsersClicked = {
                navHostController.navigate(ProfileScreen.AllUsers.route)
            },
            onChangeProfilePhotoClicked = {
                navHostController.navigate(ProfileScreen.ChangePicture.route)
            },
            onClickUpdateUserData = { userData ->
                scope.launch {
                    userViewModel.updateUserData(userData)
                }
            }
        )
    }
    composable(ProfileScreen.ChangePicture.route) {
        //the screen where we open the camera
    }
    composable(ProfileScreen.Settings.route) {
        //settings screen to log out and the darkmode option
    }
    composable(ProfileScreen.AllUsers.route) {
        // All users screen
    }
}


sealed class ProfileScreen(val route: String) {
    object Profile : ProfileScreen("PROFILE")
    object ChangePicture : ProfileScreen("CAMERA")
    object Settings : ProfileScreen("SETTINGS")
    object AllUsers : ProfileScreen("ALLUSERS")
}