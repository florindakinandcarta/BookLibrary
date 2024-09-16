package com.example.booklibrary.login

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booklibrary.MainActivity
import com.example.booklibrary.navigation.Route

@Composable
fun LoginScreenContent(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
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
                    navController.navigate(Route.ForgotPassword.routeName)
                },
                onRegisterClick = {
                    navController.navigate(Route.Register.routeName)
                },
            )
        }

        composable(route = Route.Register.routeName) {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Route.ForgotPassword.routeName) {
            ForgotPasswordScreen(
                onSendEmailClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}