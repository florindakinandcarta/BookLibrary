package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserLoginRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
import com.example.booklibrary.data.book.services.UserService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    suspend fun getUserProfile(userId: UUID): Resource<UserResponse> {
        val response = try {
            userService.getUserProfile(userId)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
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
            userService.getAllUsersWithFullName(fullName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getAllUsers(): Resource<List<UserWithRoleResponse>> {
        val response = try {
            userService.getAllUsers()
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun changeUserPassword(user: UserChangePasswordRequest): Resource<String> {
        val response = try {
            userService.changeUserPassword(user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
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
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateUserRole(user: UserUpdateRoleRequest): Resource<String> {
        val response = try {
            userService.updateUserRole(user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun registerUser(user: UserRegistrationRequest): Resource<String> {
        val response = try {
            userService.registerUser(user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun updateUserData(user: UserUpdateDataRequest): Resource<String> {
        val response = try {
            userService.updateUserData(user)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
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
            return Resource.Error(errorResponse?.message ?: "Unknown Error")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

}