package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.repo.UserRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    //call from ui
    suspend fun getUserInfo(userId: UUID): Resource<UserResponse> {
        return userRepository.getUserProfile(userId)
    }
}