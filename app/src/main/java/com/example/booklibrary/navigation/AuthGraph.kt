package com.example.booklibrary.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.booklibrary.data.book.viewModels.UserViewModel
import com.example.booklibrary.ui.login.ForgotPasswordScreen
import com.example.booklibrary.ui.login.LoginScreen
import com.example.booklibrary.ui.login.RegisterScreen
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.saveUserJWTToken
import kotlinx.coroutines.launch


fun NavGraphBuilder.authGraph(
    navHostController: NavHostController,
    dataStore: DataStore<Preferences>
) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(AuthScreen.Login.route) {
            val scope = rememberCoroutineScope()
            val userViewModel: UserViewModel = hiltViewModel()
            val userJWTToken = userViewModel.userJWTToken.collectAsState().value
            LaunchedEffect(userJWTToken) {
                when (userJWTToken) {
                    is Resource.Success -> {
                        userJWTToken.data?.let {userJWTToken ->
                            saveUserJWTToken(dataStore, userJWTToken)
                        }
                        scope.launch {
                            userViewModel.getUserInfo()
                        }
                        navHostController.popBackStack()
                        navHostController.navigate(Graph.HOME)
                    }

                    is Resource.Error -> {
                        //display error pop-up
                    }

                    else -> {
                        //Resource.Error("Something went wrong!")
                    }
                }
            }
            LoginScreen(
                onLoginClick = { userLoginRequest ->
                    scope.launch {
                        userViewModel.loginUser(userLoginRequest)
                    }
                },
                onRegisterClick = {
                    navHostController.navigate(AuthScreen.Register.route)
                },
                onForgotPasswordClick = {
                    navHostController.navigate(AuthScreen.Forgot.route)
                }
            )
        }
        composable(AuthScreen.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(AuthScreen.Forgot.route) {
            ForgotPasswordScreen(
                onSendEmailClick = {

                }
            )
        }
    }
}


sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("LOGIN")
    object Register : AuthScreen("REGISTER")
    object Forgot : AuthScreen("FORGOT")
}