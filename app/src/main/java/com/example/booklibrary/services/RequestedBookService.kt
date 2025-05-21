package com.example.booklibrary.services

import com.example.booklibrary.data.models.RequestedBook
import com.example.booklibrary.data.models.request.BookChangeStatus
import com.example.booklibrary.data.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.models.response.RequestedBookResponse
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

interface RequestedBookService {
    @GET("requested-books")
    suspend fun getAllRequestedBooks(
        @Header(AUTHORIZATION) token: String
    ): List<RequestedBook>

    @GET("/requested-books/by-book-status")
    suspend fun getRequestedBooksByBookStatus(
        @Header(AUTHORIZATION) token: String,
        @Query("status") status: String
    ): List<RequestedBook>

    @GET("requestedBook/{id}")
    suspend fun getRequestedBookById(
        @Path("id") id: UUID
    ): RequestedBook

    @GET("requestedBook/{isbn}")
    suspend fun getRequestedBookByISBN(
        @Path("isbn") isbn: String
    ): RequestedBook

    @POST("requested-books/insert-requested-book")
    suspend fun insertNewRequestedBook(
        @Header(AUTHORIZATION) token: String,
        @Body book: RequestedBookRequestDTO
    ): RequestedBookResponse

    @DELETE("requested-book/delete")
    suspend fun deleteRequestedBook(
        @Path("bookISBN") bookISBN: String
    ): String

    @PATCH("requested-books/change-book-status")
    suspend fun changeBookStatus(
        @Header(AUTHORIZATION) token: String,
        @Body book: BookChangeStatus
    ): RequestedBook

    @POST("requested-books/handle-like")
    suspend fun handleRequestedBookLike(
        @Header(AUTHORIZATION) token: String,
        @Body book: RequestedBookRequestDTO
    ): RequestedBook
}