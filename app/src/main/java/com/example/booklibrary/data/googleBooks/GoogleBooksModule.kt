package com.example.booklibrary.data.googleBooks

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoogleBooksModule {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    @Provides
    @Singleton
    fun getDataFromGoogleApi(): GoogleService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleService::class.java)
    }

}