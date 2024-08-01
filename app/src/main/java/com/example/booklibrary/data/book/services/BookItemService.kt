package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookItem
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface BookItemService {
    @GET("bookItems/{isbn}")
    suspend fun getBookItemsByBookIsbn(
        @Path("isbn") isbn: String
    ): List<BookItem>

    @POST("saveBookItem/{isbn}")
    suspend fun insertBookItem(
        @Path("isbn") isbn: String
    ): BookItem

    @DELETE("deleteBookItem/{id}")
    suspend fun deleteById(
        @Path("id") id: UUID
    ): UUID

    @POST("bookCheckout/reportDamage/{bookItemId}")
    suspend fun reportBookItemAsDamaged(
        @Path("bookItemId") bookItemId: UUID
    ): String

    @POST("bookCheckout/reportLost/{bookItemId}")
    suspend fun reportBookItemAsLost(
        @Path("bookItemId") bookItemId: UUID
    ): String
}