package com.example.booklibrary.di

import com.example.booklibrary.services.BookCheckoutService
import com.example.booklibrary.services.BookItemService
import com.example.booklibrary.services.BookService
import com.example.booklibrary.services.OfficeService
import com.example.booklibrary.services.RequestedBookService
import com.example.booklibrary.services.ReviewService
import com.example.booklibrary.services.UserService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    const val BASE_URL = ""
    val gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideBooksService(): BookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookService::class.java)
    }


    @Provides
    @Singleton
    fun provideBookCheckoutService(): BookCheckoutService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookCheckoutService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookItemService(): BookItemService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookItemService::class.java)
    }

    @Provides
    @Singleton
    fun provideOfficeService(): OfficeService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(OfficeService::class.java)
    }

    @Provides
    @Singleton
    fun provideRequestedBooksService(): RequestedBookService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RequestedBookService::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewsService(): ReviewService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ReviewService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(): UserService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserService::class.java)
    }

}