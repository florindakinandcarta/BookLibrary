package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.BookRequest
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
    suspend fun getAllRequestedBooks(): List<RequestedBook>

    @GET("requested-book/{status}/all")
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

    @POST("requested-book/insert")
    suspend fun insertNewRequestedBook(
        @Body book: BookRequest
    ): RequestedBook

    @DELETE("requested-book/delete")
    suspend fun deleteRequestedBook(
        @Path("bookISBN") bookISBN: String
    ): String

    @PATCH("requested-book/change-status")
    suspend fun changeBookStatus(
        @Body book: BookChangeStatus
    ): RequestedBook

    @POST("requested-book/handle-like")
    suspend fun handleRequestedBookLike(
        @Body book: BookRequest
    ): RequestedBook
}