package com.example.booklibrary.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel : ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth

    fun createUserWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "createUserWithEmail:success")
                            val user = auth.currentUser
                            //do something with the ui
                        } else {
                            Log.w("Firebase message", "createUserWithEmail:failue", task.exception)
                            //display something
                        }
                    }
            }
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "signINWithEmail:success")

                        } else {
                            Log.w("Firebase message", "signInWithEmail:failure", task.exception)
                            //display something
                        }
                    }
            }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "sendPasswordResetEmail:success")
                        } else {
                            Log.w(
                                "Firebase message",
                                "sendPasswordResetEmail:failure",
                                task.exception
                            )
                        }
                    }
            }
        }
    }

    fun deleteAccount(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.currentUser?.delete()
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "deleteAccount:success")
                        } else {
                            Log.w("Firebase message", "deleteAccount:failure", task.exception)

                        }
                    }
            }
        }
    }
}
