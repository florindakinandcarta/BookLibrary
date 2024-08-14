package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.UserChangePasswordRequest
import com.example.booklibrary.data.book.models.request.UserLoginRequest
import com.example.booklibrary.data.book.models.request.UserRegistrationRequest
import com.example.booklibrary.data.book.models.request.UserUpdateDataRequest
import com.example.booklibrary.data.book.models.request.UserUpdateRoleRequest
import com.example.booklibrary.data.book.models.response.UserResponse
import com.example.booklibrary.data.book.models.response.UserWithRoleResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface UserService {
    @PATCH("user/updateData")
    suspend fun updateUserData(
        @Body user: UserUpdateDataRequest
    ): String

    @POST("user/registerUser")
    suspend fun registerUser(
        @Body user: UserRegistrationRequest
    ): String

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
    suspend fun getAllUsers(
        @Query("officeName") officeName: String
    ): List<UserWithRoleResponse>

    @GET("user/getAllByFullNameContaining")
    suspend fun getAllUsersWithFullName(
        @Query("officeName") officeName: String,
        @Query("fullName") fullName: String,
    ): List<UserWithRoleResponse>

    @GET("user/profile")
    suspend fun getUserProfile(
        @Query("userId") userId: UUID
    ): UserResponse

    @POST("user/loginUser")
    suspend fun loginUser(
        @Body userLoginRequest: UserLoginRequest
    ): String
}