package com.example.booklibrary.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {
    private var auth: FirebaseAuth = Firebase.auth
    private val _message = MutableStateFlow<Resource<String>?>(null)
    val message: StateFlow<Resource<String>?> = _message
    private val _user = MutableStateFlow(auth.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    init {
        auth.addAuthStateListener {
            _user.value = it.currentUser
        }
    }

    fun createUserWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "createUserWithEmail:success")
                            _message.value = Resource.Success("User created successfully.")
                        } else {
                            Log.w("Firebase message", "createUserWithEmail:failure", task.exception)
                            _message.value = Resource.Error("Something went wrong!!!")
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
                            Log.d("Firebase message", "signInWithEmail:success")
                            _message.value = Resource.Success("Login successful.")
                        } else {
                            Log.w("Firebase message", "signInWithEmail:failure", task.exception)
                            _message.value = Resource.Error(task.exception?.message.toString())
                        }
                    }
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _message.value = Resource.Success("Sign out successful.")
    }

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("Firebase message", "sendPasswordResetEmail:success")
                            _message.value = Resource.Success("Email sent please check inbox.")
                        } else {
                            Log.w(
                                "Firebase message",
                                "sendPasswordResetEmail:failure",
                                task.exception
                            )
                            _message.value = Resource.Error(task.exception?.message.toString())
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
                            _message.value = Resource.Success("Account deleted successfully.")
                        } else {
                            Log.w("Firebase message", "deleteAccount:failure", task.exception)
                            _message.value = Resource.Error("Failed to delete acc.! Contact admin.")
                        }
                    }
            }
        }
    }
}
