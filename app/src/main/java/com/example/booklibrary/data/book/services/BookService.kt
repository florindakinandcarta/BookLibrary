package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("book/getAll")
    suspend fun getAllBooks(
        @Query("officeName") officeName: String
    ): List<BookDisplay>

    @GET("book/{isbn}")
    suspend fun getBookByISBN(
        @Path("isbn") isbn: String,
        @Query("officeName") officeName: String
    ): Book

    @GET("books")
    suspend fun getBooksByTitle(
        @Query("title") title: String,
        @Query("officeName") officeName: String
    ): List<BookDisplay>

    @GET("book/availableBooks")
    suspend fun getAvailableBooks(
        @Query("officeName") officeName: String
    ): List<BookDisplay>

    @GET("book/requestedBooks")
    suspend fun getRequestedBooks(
        @Query("officeName") officeName: String
    ): List<BookDisplay>

    @GET("books")
    suspend fun getBooksByLanguage(
        @Query("language") languages: String,
        @Query("officeName") officeName: String
    ): List<BookDisplay>

    @GET("books")
    suspend fun getBooksByGenre(
        @Query("genres") genres: String,
        @Query("officeName") officeName: String
    ): List<BookDisplay>
}