package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.response.OfficeResponse
import retrofit2.http.GET

interface OfficeService {
    @GET("office/getAll")
    suspend fun getAllOffices(): List<OfficeResponse>
}