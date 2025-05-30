package com.example.booklibrary.services

import com.example.booklibrary.data.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.models.request.UserLoginRequest
import com.example.booklibrary.data.models.request.UserRegistrationRequest
import com.example.booklibrary.data.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.models.response.UserResponse
import com.example.booklibrary.data.models.response.UserWithRoleResponse
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
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

    @PATCH("users/update-role")
    suspend fun updateUserRole(
        @Header(AUTHORIZATION) token: String,
        @Body user: UserUpdateRoleRequest
    ): String

    @DELETE("user/deleteAccount/USER_ID")
    suspend fun deleteAccount(
        @Path("USER_ID") USER_ID: UUID
    ): String

    @PATCH("users/change-password")
    suspend fun changeUserPassword(
        @Header("Authorization") token: String,
        @Body user: UserChangePasswordRequest
    ): String

    @GET("users")
    suspend fun getAllUsers(
        @Header(AUTHORIZATION) token: String,
    ): List<UserWithRoleResponse>

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