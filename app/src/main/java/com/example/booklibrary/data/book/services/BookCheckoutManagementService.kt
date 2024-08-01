package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import retrofit2.http.POST
import retrofit2.http.Path

interface BookCheckoutManagementService {
    @POST("bookCheckout/borrow/{bookCheckout}")
    suspend fun borrowBookItem(
        @Path("bookCheckout") bookCheckoutRequest: BookCheckoutRequest
    ): String

    @POST("bookCheckout/return/{bookCheckout}")
    suspend fun returnBookItem(
        @Path("bookCheckout") bookCheckoutRequest: BookCheckoutRequest
    ): String

}