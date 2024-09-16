package com.example.booklibrary.di

import com.example.booklibrary.data.book.services.BookCheckoutService
import com.example.booklibrary.data.book.services.BookItemService
import com.example.booklibrary.data.book.services.BookService
import com.example.booklibrary.data.book.services.OfficeService
import com.example.booklibrary.data.book.services.RequestedBookService
import com.example.booklibrary.data.book.services.ReviewService
import com.example.booklibrary.data.book.services.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookModule {
    private const val BASE_URL = "https://example.com/"

    @Provides
    @Singleton
    fun getBooks(): BookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookService::class.java)
    }


    @Provides
    @Singleton
    fun getBookCheckouts(): BookCheckoutService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookCheckoutService::class.java)
    }

    @Provides
    @Singleton
    fun getBookItems(): BookItemService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookItemService::class.java)
    }

    @Provides
    @Singleton
    fun getOffices(): OfficeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OfficeService::class.java)
    }

    @Provides
    @Singleton
    fun getRequestedBooks(): RequestedBookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestedBookService::class.java)
    }

    @Provides
    @Singleton
    fun getReviews(): ReviewService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReviewService::class.java)
    }

    @Provides
    @Singleton
    fun getUserData(): UserService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }

}