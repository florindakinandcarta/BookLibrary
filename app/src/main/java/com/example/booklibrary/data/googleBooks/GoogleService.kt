package com.example.booklibrary.data.googleBooks

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleService {
    @GET("volumes")
    suspend fun getGoogleBooks(
        @Query("q") query: String?,
        @Query("startIndex") startIndex: Int = 0,
        @Query("maxResults") maxResults: Int,
    ): GoogleBooks
    @GET("volumes")
    suspend fun getGoogleBooksWithISBN(
        @Query("q") query: String?
    ): GoogleBooks
}