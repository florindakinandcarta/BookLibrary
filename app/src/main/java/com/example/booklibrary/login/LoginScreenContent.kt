package com.example.booklibrary.login

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booklibrary.MainActivity
import com.example.booklibrary.navigation.Route
import com.example.booklibrary.ui.ScreensNavigator

@Composable
fun LoginScreenContent(
    screensNavigator: ScreensNavigator
) {
    val parentNavController = rememberNavController()
    screensNavigator.setParentNavController(parentNavController)

    val context = LocalContext.current
    NavHost(
        navController = parentNavController,
        startDestination = Route.Login.routeName
    ) {
        composable(route = Route.Login.routeName) {
            val loginNestedNavController = rememberNavController()
            screensNavigator.setNestedNavController(loginNestedNavController)
            NavHost(
                navController = loginNestedNavController,
                startDestination = Route.Login.routeName
            ) {
                composable(route = Route.Login.routeName) {
                    LoginScreen(
                        onLoginClick = {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        },
                        onForgotPasswordClick = {
                            screensNavigator.toRoute(Route.ForgotPassword)
                        },
                        onRegisterClick = {
                            screensNavigator.toRoute(Route.Register)
                        },
                    )
                }

                composable(route = Route.Register.routeName) {
                    RegisterScreen(
                        onLoginClick = {
                            screensNavigator.navigateBack()
                        }
                    )
                }

                composable(route = Route.ForgotPassword.routeName) {
                    ForgotPasswordScreen(
                        onSendEmailClick = {
                            screensNavigator.navigateBack()
                        }
                    )
                }
            }
        }
    }
}