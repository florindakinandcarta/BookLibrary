package com.example.booklibrary.data.book.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.booklibrary.data.book.models.Book
import com.example.booklibrary.data.book.models.BookDisplay
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.services.BookService
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookService: BookService,
    private val dataStore: DataStore<Preferences>
) {
    private val token: String by lazy {
        runBlocking {
            val jwtToken = getUserJWTToken(dataStore).first() ?: ""
            "Bearer $jwtToken"
        }
    }
    suspend fun getAllBooks(): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getAllBooks(token)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBookByISBN(isbn: String): Resource<Book> {
        val response = try {
            bookService.getBookByISBN(token,isbn)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByTitle(title: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByTitle(token,title)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getAvailableBooks(): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getAvailableBooks(token)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getRequestedBooks(): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getRequestedBooks()
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByLanguage(
        language: String
    ): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByLanguage(token,language)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun getBooksByGenre(genre: String): Resource<List<BookDisplay>> {
        val response = try {
            bookService.getBooksByGenre(token,genre)
        } catch (httpException: HttpException) {
            val errorResponse = Gson().fromJson(
                httpException.response()?.errorBody()?.string(),
                ExceptionResponse::class.java
            )
            return Resource.Error(
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }
}