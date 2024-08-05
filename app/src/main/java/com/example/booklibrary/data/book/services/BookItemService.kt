package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.BookItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface BookItemService {
    @GET("bookItems/{isbn}")
    suspend fun getBookItemsByBookIsbn(
        @Path("isbn") isbn: String,
        @Query("officeName") officeName: String
    ): List<BookItem>

    @POST("saveBookItem")
    suspend fun saveBookItem(
        @Body bookItem: BookItem
    ): BookItem

    @DELETE("deleteBookItem")
    suspend fun deleteBookItem(
        @Body bookItem: BookItem
    ): BookItem

    @POST("bookCheckout/reportDamage/{bookItemId}")
    suspend fun reportBookItemAsDamaged(
        @Path("bookItemId") bookItemId: UUID
    ): String

    @POST("bookCheckout/reportLost/{bookItemId}")
    suspend fun reportBookItemAsLost(
        @Path("bookItemId") bookItemId: UUID
    ): String
}