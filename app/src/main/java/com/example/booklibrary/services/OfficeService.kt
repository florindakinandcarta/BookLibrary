package com.example.booklibrary.services

import com.example.booklibrary.data.models.response.OfficeResponse
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.GET
import retrofit2.http.Header

interface OfficeService {
    @GET("/offices")
    suspend fun getAllOffices(
        @Header(AUTHORIZATION) token: String,
    ): List<OfficeResponse>
}