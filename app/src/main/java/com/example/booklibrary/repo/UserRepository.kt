package com.example.booklibrary.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.booklibrary.data.models.ExceptionResponse
import com.example.booklibrary.data.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.models.request.UserLoginRequest
import com.example.booklibrary.data.models.request.UserRegistrationRequest
import com.example.booklibrary.data.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.models.response.UserResponse
import com.example.booklibrary.data.models.response.UserWithRoleResponse
import com.example.booklibrary.services.UserService
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val dataStore: DataStore<Preferences>
) {
    private val token: String by lazy {
        runBlocking {
            val jwtToken = getUserJWTToken(dataStore).first() ?: ""
            "Bearer $jwtToken"
        }
    }

    suspend fun getUserProfile(): Resource<UserResponse> {
        val response = try {
            userService.getUserProfile(token = token)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getAllUsersWithFullName(
        fullName: String
    ): Resource<List<UserWithRoleResponse>> {
        val response = try {
            userService.getAllUsersWithFullName(token = token, fullName = fullName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getAllUsers(): Resource<List<UserWithRoleResponse>> {
        val response = try {
            userService.getAllUsers(token)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun changeUserPassword(user: UserChangePasswordRequest): Resource<String> {
        val response = try {
            userService.changeUserPassword(token, user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun deleteAccount(userId: UUID): Resource<String> {
        val response = try {
            userService.deleteAccount(userId)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateUserRole(user: UserUpdateRoleRequest): Resource<String> {
        val response = try {
            userService.updateUserRole(token, user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun registerUser(user: UserRegistrationRequest): Resource<UserWithRoleResponse> {
        val response = try {
            userService.registerUser(user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateUserData(user: UserUpdateDataRequest): Resource<String> {
        val response = try {
            userService.updateUserData(token, user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun loginUser(userLoginRequest: UserLoginRequest): Resource<String> {
        val response = try {
            userService.loginUser(userLoginRequest)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.generalExceptionMessage ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

}