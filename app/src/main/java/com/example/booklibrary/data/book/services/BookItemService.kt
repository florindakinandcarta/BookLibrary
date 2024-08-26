package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface BookItemService {
    @GET("bookItems")
    suspend fun getBookItemsByBookIsbn(
        @Query("isbn") isbn: String
    ): List<BookItem>

    @POST("saveBookItem")
    suspend fun saveBookItem(
        @Body bookID: BookID
    ): BookItem

    @POST("deleteBookItem/{id}")
    suspend fun deleteBookItem(
        @Path("id") id: UUID
    ): UUID

    @PATCH("book-items/report-damaged/{id}")
    suspend fun reportBookItemAsDamaged(
        @Path("id") id: UUID
    ): UUID

    @PATCH("book-items/report-lost/{id}")
    suspend fun reportBookItemAsLost(
        @Path("id") id: UUID
    ): UUID
}