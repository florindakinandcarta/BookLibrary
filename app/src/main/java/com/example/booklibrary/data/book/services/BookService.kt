package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookState
import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.request.BookInsertRequest
import com.example.booklibrary.util.AUTHORIZATION
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface BookService {
    @GET("books")
    suspend fun getAllBooks(
        @Header(AUTHORIZATION) token: String
    ): List<BookDisplay>

    @GET("books/get-book")
    suspend fun getBookByISBN(
        @Header(AUTHORIZATION) token: String,
        @Query("isbn") isbn: String
    ): Book

    @GET("books/available")
    suspend fun getAvailableBooks(
        @Header(AUTHORIZATION) token: String
    ): List<BookDisplay>

    @GET("books/paginated-books")
    suspend fun getAllAvailableBooksPaginated(
        @Query("bookStatus") bookStatus: BookStatus,
        @Query("bookItemState") bookItemState: BookState,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<BookDisplay>

    @GET("books/by-title")
    suspend fun getBooksByTitle(
        @Header(AUTHORIZATION) token: String,
        @Query("titleSearchTerm") title: String
    ): List<BookDisplay>

    @GET("book/requested-books")
    suspend fun getRequestedBooks(): List<BookDisplay>

    @GET("books/by-language")
    suspend fun getBooksByLanguage(
        @Header(AUTHORIZATION) token: String,
        @Query("language") languages: String
    ): List<BookDisplay>

    @GET("books/by-genres")
    suspend fun getBooksByGenre(
        @Header(AUTHORIZATION) token: String,
        @Query("genres") genres: String
    ): List<BookDisplay>

    @POST("books/insert")
    suspend fun insertNewAvailableBook(
        @Body bookInsertRequest: BookInsertRequest
    ): BookDisplay

    @DELETE("books")
    suspend fun deleteBook(
        @Query("isbn") isbn: String
    ): BookID
}