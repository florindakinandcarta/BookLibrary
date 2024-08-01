package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface BookCheckoutQueryService {
    @GET("bookCheckout/all/{officeName}")
    suspend fun getAllBookCheckouts(
        @Path("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckout/{officeName}")
    suspend fun getAllBookCheckoutsPaginated(
        @Path("officeName") officeName: String,
        @Query("numberOfPages") numberOfPages: Int,
        @Query("pageSize") pageSize: Int
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckout/active/{officeName}")
    suspend fun getAllActiveBookCheckouts(
        @Path("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckout/{officeName}")
    suspend fun getAllPastBookCheckouts(
        @Path("officeName") officeName: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckout/{officeName}/{titleSearchTerm}")
    suspend fun getAllBookCheckoutsForBookTitle(
        @Path("officeName") officeName: String,
        @Path("titleSearchTerm") titleSearchTerm: String
    ): List<BookCheckoutWithUserAndBookItemResponse>

    @GET("bookCheckout/{userId}")
    suspend fun getAllBookCheckoutsFromUserWithId(
        @Path("userId") userId: UUID
    ): List<BookCheckoutResponse>
}