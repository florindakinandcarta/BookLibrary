package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.services.BookService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookService: BookService
) {
    suspend fun getAllBooks(officeName: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getAllBooks(officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBookByISBN(isbn: String, officeName: String): Resource<Book> {
        val response = try {
            bookService.getBookByISBN(isbn, officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByTitle(title: String, officeName: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByTitle(title, officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getAvailableBooks(officeName: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getAvailableBooks(officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getRequestedBooks(officeName: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getRequestedBooks(officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByLanguage(
        language: String,
        officeName: String
    ): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByLanguage(language, officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByGenre(genre: String, officeName: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByGenre(genre, officeName)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.message ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}