package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.request.BookReturnRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.UUID

interface BookCheckoutService {
    @GET("book-checkouts")
    suspend fun getAllBookCheckouts(
        @Header(AUTHORIZATION) token: String,
    ): List<BookCheckoutWithUserAndBookItemResponse>
    @GET("book-checkouts/getAllPaginated")
    suspend fun getAllBookCheckoutsPaginated(
        @Query("numberOfPages") numberOfPages: Int = 0,
        @Query("pageSize") pageSize: Int = 5
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/active")
    suspend fun getAllActiveBookCheckouts(
        @Header(AUTHORIZATION) token: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/past")
    suspend fun getAllPastBookCheckouts(
        @Header(AUTHORIZATION) token: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/by-title")
    suspend fun getAllBookCheckoutsForBookTitle(
        @Header(AUTHORIZATION) token: String,
        @Query("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/by-user")
    suspend fun getAllBookCheckoutsForUser(
        @Header(AUTHORIZATION) token: String,
    ): List<BookCheckoutResponse>

    @GET("book-checkouts/near-return-date")
    suspend fun getAllNearReturnDate(
        @Header(AUTHORIZATION) token: String
    ): List<BookCheckoutReturnReminderResponse>

    @GET("bookCheckoutQuery/getAllBooksForUserByTitleContaining")
    suspend fun getAllBooksForUserByTitleContaining(
        @Query("userId") userId: UUID,
        @Query("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutResponse>

    @POST("book-checkouts/borrow")
    suspend fun borrowBookItem(
        @Header(AUTHORIZATION) token: String,
        @Body bookCheckoutRequest: BookCheckoutRequest
    ): BookCheckoutResponse

    @POST("book-checkouts/return")
    suspend fun returnBookItem(
        @Header(AUTHORIZATION) token: String,
        @Body bookCheckoutRequest: BookReturnRequest
    ): BookCheckoutResponse
}