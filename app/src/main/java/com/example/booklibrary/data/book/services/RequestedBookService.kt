package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.book.models.response.RequestedBookResponse
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
        @Header("Authorization") token: String
    ): List<RequestedBook>

    @GET("/requested-books/by-book-status")
    suspend fun getRequestedBooksByBookStatus(
        @Header("Authorization") token: String,
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
        @Header("Authorization") token: String,
        @Body book: RequestedBookRequestDTO
    ): RequestedBookResponse

    @DELETE("requested-book/delete")
    suspend fun deleteRequestedBook(
        @Path("bookISBN") bookISBN: String
    ): String

    @PATCH("requested-books/change-book-status")
    suspend fun changeBookStatus(
        @Header("Authorization") token: String,
        @Body book: BookChangeStatus
    ): RequestedBook

    @POST("requested-books/handle-like")
    suspend fun handleRequestedBookLike(
        @Header("Authorization") token: String,
        @Body book: RequestedBookRequestDTO
    ): RequestedBook
}