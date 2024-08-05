package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface BookCheckoutManagementService {
    @POST("bookCheckoutManagement/borrow")
    suspend fun borrowBookItem(
        @Body bookCheckoutRequest: BookCheckoutRequest
    ): BookCheckoutResponse

    @POST("bookCheckoutManagement/return")
    suspend fun returnBookItem(
       @Body bookCheckoutRequest: BookCheckoutRequest
    ): BookCheckoutResponse

}