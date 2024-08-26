package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.UUID

interface BookCheckoutService {
    @GET("book-checkouts/getAll")
    suspend fun getAllBookCheckouts(): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/getAllPaginated")
    suspend fun getAllBookCheckoutsPaginated(
        @Query("numberOfPages") numberOfPages: Int = 0,
        @Query("pageSize") pageSize: Int = 5
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/getAllActive")
    suspend fun getAllActiveBookCheckouts(): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/getAllPast")
    suspend fun getAllPastBookCheckouts(): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/getAllByTitleContaining")
    suspend fun getAllBookCheckoutsForBookTitle(
        @Query("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("book-checkouts/getAllBooksForUser")
    suspend fun getAllBookCheckoutsForUserWithId(
        @Query("userId") userId: UUID
    ): List<BookCheckoutResponse>

    @GET("book-checkouts/getAllNearReturnDate")
    suspend fun getAllNearReturnDate(): List<BookCheckoutReturnReminderResponse>

    @GET("bookCheckoutQuery/getAllBooksForUserByTitleContaining")
    suspend fun getAllBooksForUserByTitleContaining(
        @Query("userId") userId: UUID,
        @Query("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutResponse>

    @POST("book-checkouts/borrow")
    suspend fun borrowBookItem(
        @Body bookCheckoutRequest: BookCheckoutRequest
    ): BookCheckoutResponse

    @POST("book-checkouts/return")
    suspend fun returnBookItem(
        @Body bookCheckoutRequest: BookCheckoutRequest
    ): BookCheckoutResponse
}