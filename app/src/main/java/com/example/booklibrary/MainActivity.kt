package com.example.booklibrary

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booklibrary.data.book.viewModels.AuthViewModel
import com.example.booklibrary.login.LoginActivity
import com.example.booklibrary.navigation.MainScreen
import com.example.booklibrary.ui.theme.BookLibraryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookLibraryTheme {
                val authViewModel: AuthViewModel = hiltViewModel()
                val user by authViewModel.user.collectAsState()
                if (user != null) {
                    MainScreen()
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    this.startActivity(intent)
                    finish()
                }
            }
        }
    }
}


