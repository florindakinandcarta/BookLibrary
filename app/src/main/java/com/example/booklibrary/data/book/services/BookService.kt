package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookState
import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.request.BookInsertRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookService {
    @GET("books")
    suspend fun getAllBooks(
    ): List<BookDisplay>

    @GET("books/book")
    suspend fun getBookByISBN(
        @Query("isbn") isbn: String
    ): Book

    @GET("books/available-books")
    suspend fun getAvailableBooks(
    ): List<BookDisplay>

    @GET("books/paginated-books")
    suspend fun getAllAvailableBooksPaginated(
        @Query("bookStatus") bookStatus: BookStatus,
        @Query("bookItemState") bookItemState: BookState,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<BookDisplay>

    @GET("books")
    suspend fun getBooksByTitle(
        @Query("title") title: String
    ): List<BookDisplay>

    @GET("book/requested-books")
    suspend fun getRequestedBooks(): List<BookDisplay>

    @GET("books")
    suspend fun getBooksByLanguage(
        @Query("language") languages: String
    ): List<BookDisplay>

    @GET("books")
    suspend fun getBooksByGenre(
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