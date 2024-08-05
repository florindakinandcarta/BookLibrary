package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.RequestedBook
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface RequestedBookService {
    @GET("requested-book/all")
    suspend fun getAllRequestedBooks(
        @Query("officeName") officeName: String
    ): List<RequestedBook>

    @GET("requested-book/{status}/all")
    suspend fun getRequestedBooksByBookStatus(
        @Path("status") status: BookStatus,
        @Query("officeName") officeName: String
    ): List<RequestedBook>

    @GET("requestedBook/{id}")
    suspend fun getRequestedBookById(
        @Path("id") id: UUID
    ): RequestedBook

    @GET("requestedBook/{isbn}")
    suspend fun getRequestedBookByISBN(
        @Path("isbn") isbn: String
    ): RequestedBook

    @POST("requested-book/new")
    suspend fun insertNewRequestedBook(
        @Body bookISBN: String
    ): RequestedBook

    @DELETE("requested-book/{bookISBN}/delete")
    suspend fun deleteRequestedBook(
        @Path("bookISBN") bookISBN: String,
        @Query("officeName") officeName: String
    ): String

    @PATCH("requested-book/{requestedBookId}/change-status")
    suspend fun changeBookStatus(
        @Path("requestedBookId") requestedBookId: UUID,
        @Path("bookStatus") bookStatus: BookStatus
    ): RequestedBook

    @POST("requested-book/{requestedBookId}/handle-like")
    suspend fun handleRequestedBookLike(
        @Path("requestedBookId") requestedBookId: UUID,
        @Query("status") status: BookStatus
    ): RequestedBook
}