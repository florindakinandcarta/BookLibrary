package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface BookCheckoutQueryService {
    @GET("bookCheckoutQuery/getAll")
    suspend fun getAllBookCheckouts(
        @Query("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckoutQuery/getAllPaginated")
    suspend fun getAllBookCheckoutsPaginated(
        @Query("officeName") officeName: String,
        @Query("numberOfPages") numberOfPages: Int,
        @Query("pageSize") pageSize: Int
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckoutQuery/getAllActive")
    suspend fun getAllActiveBookCheckouts(
        @Query("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckoutQuery/getAllPast")
    suspend fun getAllPastBookCheckouts(
        @Query("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckoutQuery/getAllByTitleContaining")
    suspend fun getAllBookCheckoutsForBookTitle(
        @Query("titleSearchTerm") titleSearchTerm: String,
        @Query("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckoutQuery/getAllBooksForUser/{USER_ID}")
    suspend fun getAllBookCheckoutsForUserWithId(
        @Path("USER_ID") USER_ID: UUID
    ): List<BookCheckoutResponse>

    @GET("bookCheckoutQuery/getAllNearReturnDate")
    suspend fun getAllNearReturnDate(
        @Query("officeName") officeName: String
    ): List<BookCheckoutReturnReminderResponse>

    @GET("bookCheckoutQuery/getAllBooksForUserByTitleContaining/{USER_ID}")
    suspend fun getAllBooksForUserByTitleContaining(
        @Path("USER_ID") USER_ID: UUID,
        @Query("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutResponse>
}