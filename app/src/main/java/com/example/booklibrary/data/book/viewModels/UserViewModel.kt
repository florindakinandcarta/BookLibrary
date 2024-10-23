package com.example.booklibrary.data.book.viewModels

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserLoginRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
import com.example.booklibrary.data.book.repo.UserRepository
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.saveUserJWTToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {


    private val _user = MutableStateFlow<Resource<UserResponse>>(Resource.Loading())
    val user: StateFlow<Resource<UserResponse>> = _user

    private val _userWithRole = MutableStateFlow<Resource<UserWithRoleResponse>>(Resource.Loading())
    val userWithRole: StateFlow<Resource<UserWithRoleResponse>> = _userWithRole

    private val _response = MutableStateFlow<Resource<String>>(Resource.Loading())
    val response: StateFlow<Resource<String>> = _response

    private val _users = MutableStateFlow<Resource<List<UserWithRoleResponse>>>(Resource.Loading())
    val users: StateFlow<Resource<List<UserWithRoleResponse>>> = _users

    suspend fun getUserInfo(userId: UUID): Resource<UserResponse> {
        return userRepository.getUserProfile(userId)
    }

    suspend fun getAllUsersWithFullName(
        fullName: String
    ): Resource<List<UserWithRoleResponse>> {
        return userRepository.getAllUsersWithFullName(fullName)
    }

    suspend fun getAllUsers() {
        viewModelScope.launch {
            _users.value = Resource.Loading()
            val response = userRepository.getAllUsers()
            _users.value = response
        }
    }

    suspend fun changePassword(user: UserChangePasswordRequest) {
        viewModelScope.launch {
            _response.value = Resource.Loading()
            val response = userRepository.changeUserPassword(user)
            _response.value = response
        }
    }

    suspend fun deleteAccount(userId: UUID) {
        _response.value = Resource.Loading()
        val response = userRepository.deleteAccount(userId)
        _response.value = response
    }

    suspend fun updateUserRole(user: UserUpdateRoleRequest) {
        _response.value = Resource.Loading()
        val response = userRepository.updateUserRole(user)
        _response.value = response
    }

    suspend fun registerUser(user: UserRegistrationRequest) {
        _userWithRole.value = Resource.Loading()
        val response = userRepository.registerUser(user)
        _userWithRole.value = response
    }

    suspend fun updateUserData(user: UserUpdateDataRequest) {
        viewModelScope.launch {
            _response.value = Resource.Loading()
            val response = userRepository.updateUserData(user)
            _response.value = response
        }
    }

    suspend fun loginUser(userLoginRequest: UserLoginRequest) {
        viewModelScope.launch {
            when (val response = userRepository.loginUser(userLoginRequest)) {
                is Resource.Success -> {
                    response.data?.let { token ->
                        saveUserJWTToken(dataStore, token)
                    }
//                    _userJWTToken.value = Resource.Success(response.data!!)
                }

                is Resource.Error -> {
                    //display error pop-up
                }

                else -> {
//                    _userJWTToken.value = Resource.Error("Something went wrong!")
                }
            }
        }
    }

    fun signOutUser() {
        viewModelScope.launch {
            saveUserJWTToken(dataStore, "")
        }
    }
}