package com.example.booklibrary.services

import com.example.booklibrary.data.models.BookID
import com.example.booklibrary.data.models.BookItem
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface BookItemService {
    @GET("book-items")
    suspend fun getBookItemsByBookIsbn(
        @Header(AUTHORIZATION) token: String,
        @Query("isbn") isbn: String
    ): List<BookItem>

    @POST("book-items/insert")
    suspend fun createBookItem(
        @Header(AUTHORIZATION) token: String,
        @Body isbn: BookID
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