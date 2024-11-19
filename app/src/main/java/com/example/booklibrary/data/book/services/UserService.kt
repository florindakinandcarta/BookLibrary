package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserLoginRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface UserService {
    @PATCH("users/update-data")
    suspend fun updateUserData(
        @Header(AUTHORIZATION) token: String,
        @Body user: UserUpdateDataRequest
    ): String

    @POST("users/register")
    suspend fun registerUser(
        @Body user: UserRegistrationRequest
    ): UserWithRoleResponse

    @POST("user/updateRole")
    suspend fun updateUserRole(
        @Body user: UserUpdateRoleRequest
    ): String

    @DELETE("user/deleteAccount/USER_ID")
    suspend fun deleteAccount(
        @Path("USER_ID") USER_ID: UUID
    ): String

    @PUT("user/changePassword")
    suspend fun changeUserPassword(
        @Body user: UserChangePasswordRequest
    ): String

    @GET("user/getAll")
    suspend fun getAllUsers(): List<UserWithRoleResponse>

    @GET("users/by-full-name")
    suspend fun getAllUsersWithFullName(
        @Header(AUTHORIZATION) token: String,
        @Query("fullName") fullName: String,
    ): List<UserWithRoleResponse>

    @GET("users/profile")
    suspend fun getUserProfile(
        @Header(AUTHORIZATION) token: String
    ): UserResponse

    @POST("users/login")
    suspend fun loginUser(
        @Body userLoginRequest: UserLoginRequest
    ): String
}