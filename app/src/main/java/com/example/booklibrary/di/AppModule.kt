package com.example.booklibrary.di

import com.example.booklibrary.data.book.services.BookCheckoutService
import com.example.booklibrary.data.book.services.BookItemService
import com.example.booklibrary.data.book.services.BookService
import com.example.booklibrary.data.book.services.OfficeService
import com.example.booklibrary.data.book.services.RequestedBookService
import com.example.booklibrary.data.book.services.ReviewService
import com.example.booklibrary.data.book.services.UserService
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
//    private const val BASE_URL = "http://192.168.100.97:8080/"
    private const val BASE_URL = "http://10.100.0.70:8080"
//private const val BASE_URL = "http://192.168.1.12:8080"
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