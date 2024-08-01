package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface UserService {
    @PUT("updateUserData/{user}")
    suspend fun updateUserData(
        @Path("user") user: UserUpdateDataRequest
    ): String

    @POST("registerUser/{user}")
    suspend fun registerUser(
        @Path("user") user: UserRegistrationRequest
    ): String

    @PUT("updateUserRole/{user}")
    suspend fun updateUserRole(
        @Path("user") user: UserUpdateRoleRequest
    ): String

    @DELETE("deleteAccount/{userId}")
    suspend fun deleteAccount(
        @Path("user") userId: UUID
    ): String

    @PUT("changeUserPassword/{user}")
    suspend fun changeUserPassword(
        @Path("user") user: UserChangePasswordRequest
    ): String

    @GET("users/all/{officeName}")
    suspend fun getAllUsers(
        @Path("officeName") officeName: String
    ): List<UserWithRoleResponse>

    @GET("users/all/{officeName}/{fullName}")
    suspend fun getAllUsersWithFullName(
        @Path("officeName") officeName: String,
        @Path("fullName") fullName: String,
    ): List<UserWithRoleResponse>

    @GET("users/{userId}")
    suspend fun getUserProfile(
        @Path("userId") userId: UUID
    ): UserResponse
}