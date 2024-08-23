package com.example.booklibrary.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.booklibrary.ui.ScreensNavigator
import com.example.booklibrary.ui.theme.BookLibraryTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookLibraryTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val screensNavigator = ScreensNavigator()
                    LoginScreenContent(screensNavigator = screensNavigator)
                }
            }
        }
    }
}