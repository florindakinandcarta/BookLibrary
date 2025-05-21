package com.example.booklibrary.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.booklibrary.ui.userProfile.AllUsersScreen
import com.example.booklibrary.ui.userProfile.ChangePasswordScreen
import com.example.booklibrary.ui.userProfile.ProfileScreen
import com.example.booklibrary.ui.userProfile.Settings
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.showToast
import com.example.booklibrary.viewModels.UserViewModel
import kotlinx.coroutines.launch

fun NavGraphBuilder.profileGraph(navHostController: NavHostController) {
    composable(BottomTab.Profile.route) {
        val userViewModel: UserViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val context = LocalContext.current
        val responseData = userViewModel.responseData.collectAsState().value
        LaunchedEffect(responseData) {
            when (responseData) {
                is Resource.Success -> {
                    context.showToast("Successfully changed")
                }

                is Resource.Error -> {
                    if (responseData.message.toString() == "JSON document was not fully consumed.") {
                        context.showToast("Successfully changed")
                    } else {
                        context.showToast(responseData.message.toString())
                    }
                }

                is Resource.Loading -> {

                }
            }
        }
        LaunchedEffect(Unit) {
            scope.launch {
                userViewModel.getUserInfo()
            }
        }
        ProfileScreen(
            onChangePasswordClicked = {
                navHostController.navigate(ProfileScreen.ChangePassword.route)
            },
            onAllUsersClicked = {
                navHostController.navigate(ProfileScreen.AllUsers.route)
            },
            onClickUpdateUserData = { userData ->
                scope.launch {
                    userViewModel.updateUserData(userData)
                }
            },
            onSettingsClicked = {
                navHostController.navigate(ProfileScreen.Settings.route)
            }
        )
    }
    composable(ProfileScreen.ChangePicture.route) {
        //the screen where we open the camera
    }

    composable(ProfileScreen.Settings.route) {
        Settings(
            onBackClicked = {
                navHostController.popBackStack()
            }
        )
    }
    composable(ProfileScreen.ChangePassword.route) {
        val userViewModel: UserViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        val response = userViewModel.response.collectAsState().value
        val context = LocalContext.current
        LaunchedEffect(response) {
            when (response) {
                is Resource.Success -> {
                    context.showToast("Successfully changed")
                }

                is Resource.Error -> {
                    if (response.message.toString() == "JSON document was not fully consumed.") {
                        context.showToast("Successfully changed")
                    } else {
                        context.showToast(response.message.toString())
                    }
                }

                is Resource.Loading -> {

                }
            }
        }
        LaunchedEffect(Unit) {
            scope.launch {
                userViewModel.getUserInfo()
            }
        }
        ChangePasswordScreen(
            onBackClicked = {
                navHostController.popBackStack()
            },
            onChangePasswordRequest = { userChangePasswordRequest ->
                scope.launch {
                    userViewModel.changePassword(userChangePasswordRequest)
                }
            }
        )
    }
    composable(ProfileScreen.AllUsers.route) {
        val userViewModel: UserViewModel = hiltViewModel()
        val scope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            scope.launch {
                userViewModel.getAllUsers()
            }
        }
        val responseRole = userViewModel.responseRole.collectAsState().value
        val context = LocalContext.current
        LaunchedEffect(responseRole) {
            when (responseRole) {
                is Resource.Success -> {
                    context.showToast("Role updated successfully")
                }

                is Resource.Error -> {
                    if (responseRole.message.toString() == "JSON document was not fully consumed.") {
                        context.showToast("Role updated successfully")
                    } else {
                        context.showToast(responseRole.message.toString())
                    }
                }

                is Resource.Loading -> {

                }
            }
        }
        AllUsersScreen(
            onBackClicked = {
                navHostController.popBackStack()
            },
            onChangeRoleClicked = { userRole ->
                scope.launch {
                    userViewModel.updateUserRole(userRole)
                }
            }
        )
    }
}


sealed class ProfileScreen(val route: String) {
    object ChangePicture : ProfileScreen("CAMERA")
    object Settings : ProfileScreen("SETTINGS")
    object ChangePassword : ProfileScreen("CHANGEPASSWORD")
    object AllUsers : ProfileScreen("ALLUSERS")
}