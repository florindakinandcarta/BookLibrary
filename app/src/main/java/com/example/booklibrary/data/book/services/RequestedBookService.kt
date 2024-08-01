package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.RequestedBook
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface RequestedBookService {
    @GET("requestedBook/all")
    suspend fun getAllRequestedBooks(): List<RequestedBook>

    @GET("requestedBook/{status}")
    suspend fun getRequestedBooksByBookStatus(
        @Path("status") status: BookStatus
    ): List<RequestedBook>

    @GET("requestedBook/{id}")
    suspend fun getRequestedBookById(
        @Path("id") id: UUID
    ): RequestedBook

    @GET("requestedBook/{isbn}")
    suspend fun getRequestedBookByISBN(
        @Path("isbn") isbn: String
    ): RequestedBook

    @POST("requestedBook/{bookISBN}")
    suspend fun saveRequestedBook(
        @Path("bookISBN") bookISBN: String
    ): RequestedBook

    @DELETE("requestedBook/delete/{bookISBN}")
    suspend fun deleteRequestedBook(
        @Path("bookISBN") bookISBN: String
    ): String

    @PATCH("requestedBook/{bookISBN}/{bookStatus}")
    suspend fun changeBookStatus(
        @Path("requestedBookId") requestedBookId: UUID,
        @Path("bookStatus") bookStatus: BookStatus
    ): RequestedBook

    @PATCH("requestedBook/{bookISBN}/like")
    suspend fun handleRequestedBookLike(
        @Path("requestedBookId") requestedBookId: UUID,
    ): RequestedBook
}