package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.response.UserResponse
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
            return Resource.Error(errorResponse?.message ?: "")
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}