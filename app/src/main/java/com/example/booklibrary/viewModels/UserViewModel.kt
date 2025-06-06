package com.example.booklibrary.viewModels

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.booklibrary.data.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.models.request.UserLoginRequest
import com.example.booklibrary.data.models.request.UserRegistrationRequest
import com.example.booklibrary.data.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.models.response.UserResponse
import com.example.booklibrary.data.models.response.UserWithRoleResponse
import com.example.booklibrary.repo.UserRepository
import com.example.booklibrary.ui.worker.LogoutWorker
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserRole
import com.example.booklibrary.util.saveUserJWTToken
import com.example.booklibrary.util.saveUserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: DataStore<Preferences>,
    application: Application
) : ViewModel() {


    private val _user = MutableStateFlow<Resource<UserResponse>>(Resource.Loading())
    val user: StateFlow<Resource<UserResponse>> = _user

    private val _usersWithRole =
        MutableStateFlow<Resource<UserWithRoleResponse>>(Resource.Loading())
    val usersWithRole: StateFlow<Resource<UserWithRoleResponse>> = _usersWithRole

    private val _response = MutableStateFlow<Resource<String>>(Resource.Loading())
    val response: StateFlow<Resource<String>> = _response

    private val _responseRole = MutableStateFlow<Resource<String>>(Resource.Loading())
    val responseRole: StateFlow<Resource<String>> = _responseRole

    private val _responseData = MutableStateFlow<Resource<String>>(Resource.Loading())
    val responseData: StateFlow<Resource<String>> = _responseData

    private val _users = MutableStateFlow<Resource<List<UserWithRoleResponse>>>(Resource.Loading())
    val users: StateFlow<Resource<List<UserWithRoleResponse>>> = _users


    private val _userJWTToken = MutableStateFlow<Resource<String>>(Resource.Loading())
    val userJWTToken: StateFlow<Resource<String>> = _userJWTToken

    val isUserAdminFlow = getUserRole(dataStore)

    private val _userInfo = MutableStateFlow<Resource<UserResponse>>(Resource.Loading())
    val userInfo: StateFlow<Resource<UserResponse>> = _userInfo

    private val workManager = WorkManager.getInstance(application)


    private fun scheduleLogout() {
        val workRequest = OneTimeWorkRequestBuilder<LogoutWorker>()
            .setInitialDelay(30, TimeUnit.MINUTES)
            .build()

        workManager.enqueue(workRequest)
    }

    suspend fun getUserInfo() {
        when (val response = userRepository.getUserProfile()) {
            is Resource.Success -> {
                response.data?.fullName?.let { fullName ->
                    viewModelScope.launch {
                        getAllUsersWithFullName(fullName)
                    }
                }
                _userInfo.value = response
            }

            is Resource.Error -> {
                _userInfo.value = response
            }

            is Resource.Loading -> {
                _userInfo.value = Resource.Loading()
            }
        }
    }

    private suspend fun getAllUsersWithFullName(fullName: String) {
        when (userRepository.getAllUsersWithFullName(fullName)) {
            is Resource.Success -> {
                viewModelScope.launch {
                    saveUserRole(dataStore, true)
                }
            }

            is Resource.Error -> {
                saveUserRole(dataStore, false)
            }

            is Resource.Loading -> {

            }
        }
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
        _responseRole.value = Resource.Loading()
        val response = userRepository.updateUserRole(user)
        _responseRole.value = response
    }

    suspend fun registerUser(user: UserRegistrationRequest) {
        _usersWithRole.value = Resource.Loading()
        val response = userRepository.registerUser(user)
        _usersWithRole.value = response
    }

    suspend fun updateUserData(user: UserUpdateDataRequest) {
        viewModelScope.launch {
            _responseData.value = Resource.Loading()
            val response = userRepository.updateUserData(user)
            _responseData.value = response
        }
    }

    suspend fun loginUser(userLoginRequest: UserLoginRequest) {
        viewModelScope.launch {
            when (val response = userRepository.loginUser(userLoginRequest)) {
                is Resource.Success -> {
                    _userJWTToken.value = response
                    scheduleLogout()
                }

                is Resource.Error -> {
                    _userJWTToken.value = response
                }

                is Resource.Loading -> {
                    _userJWTToken.value = Resource.Loading()
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