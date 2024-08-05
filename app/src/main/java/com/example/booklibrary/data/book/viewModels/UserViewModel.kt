package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
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

    suspend fun getAllUsersWithFullName(
        officeName: String,
        fullName: String
    ): Resource<List<UserWithRoleResponse>> {
        return userRepository.getAllUsersWithFullName(officeName, fullName)
    }

    suspend fun getAllUsers(officeName: String): Resource<List<UserWithRoleResponse>> {
        return userRepository.getAllUsers(officeName)
    }

    suspend fun changePassword(user: UserChangePasswordRequest): Resource<String> {
        return userRepository.changeUserPassword(user)
    }

    suspend fun deleteAccount(userId: UUID): Resource<String> {
        return userRepository.deleteAccount(userId)
    }

    suspend fun updateUserRole(user: UserUpdateRoleRequest): Resource<String> {
        return userRepository.updateUserRole(user)
    }

    suspend fun registerUser(user: UserRegistrationRequest): Resource<String> {
        return userRepository.registerUser(user)
    }

    suspend fun updateUserData(user: UserUpdateDataRequest): Resource<String> {
        return userRepository.updateUserData(user)
    }
}