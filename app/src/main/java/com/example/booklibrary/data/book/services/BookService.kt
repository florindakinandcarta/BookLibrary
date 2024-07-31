package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {
    @GET
    suspend fun getAllBooks(): List<Book>

    @GET("book/{isbn}")
    suspend fun getBookByISBN(
        @Path("isbn") isbn: String
    ): Book

    @GET("books/{title}")
    suspend fun getBooksByTitle(
        @Path("title") title: String
    ): List<Book>

    @GET("availableBooks")
    suspend fun getAvailableBooks(): List<BookDisplay>

    @GET("requestedBooks")
    suspend fun getRequestedBooks(): List<BookDisplay>

    @GET("books/{language}")
    suspend fun getBooksByLanguage(
        @Path("language") languages: String
    ): List<BookDisplay>

    @GET("books/{genre}")
    suspend fun getBooksByGenre(
        @Path("genre") genre: String
    ): List<BookDisplay>
}