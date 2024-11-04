package com.example.booklibrary.data.book.repo

import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.models.request.BookChangeStatus
import com.example.booklibrary.data.book.models.request.RequestedBookRequestDTO
import com.example.booklibrary.data.book.models.response.RequestedBookResponse
import com.example.booklibrary.data.book.services.RequestedBookService
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class RequestedBookRepository @Inject constructor(
    private val requestedBookService: RequestedBookService,
    private val dataStore: DataStore<Preferences>
) {
    private val token: String by lazy {
        runBlocking {
            val jwtToken = getUserJWTToken(dataStore).first() ?: ""
            "Bearer $jwtToken"
        }
    }
    suspend fun getAllRequestedBooks(): Resource<List<RequestedBook>> {
        val response = try {
            requestedBookService.getAllRequestedBooks(token)
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

    suspend fun getRequestedBookById(id: UUID): Resource<RequestedBook> {
        val response = try {
            requestedBookService.getRequestedBookById(id)
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

    suspend fun getRequestedBookByISBN(isbn: String): Resource<RequestedBook> {
        val response = try {
            requestedBookService.getRequestedBookByISBN(isbn)
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

    suspend fun insertNewRequestedBook(book: RequestedBookRequestDTO): Resource<RequestedBookResponse> {
        val response = try {
            requestedBookService.insertNewRequestedBook(token = token, book = book)
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

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        val response = try {
            requestedBookService.deleteRequestedBook(bookIsbn)
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

    suspend fun changeBookStatus(
        bookStatus: BookChangeStatus
    ): Resource<RequestedBook> {
        val response = try {
            requestedBookService.changeBookStatus(token = token, book = bookStatus)
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

    suspend fun handleRequestedBookLike(
        status: RequestedBookRequestDTO
    ): Resource<RequestedBook> {
        val response = try {
            requestedBookService.handleRequestedBookLike(token = token, book = status)
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

    suspend fun getRequestedBooksByBookStatus(
        status: String
    ): Resource<List<RequestedBook>> {
        val response = try {
            requestedBookService.getRequestedBooksByBookStatus(token = token,status = status)
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