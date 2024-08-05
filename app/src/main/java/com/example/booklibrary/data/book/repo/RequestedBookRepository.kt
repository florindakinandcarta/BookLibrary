package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.BookStatus
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.RequestedBook
import com.example.booklibrary.data.book.services.RequestedBookService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class RequestedBookRepository @Inject constructor(
    private val requestedBookService: RequestedBookService
) {
    suspend fun getAllRequestedBooks(): Resource<List<RequestedBook>> {
        val response = try {
            requestedBookService.getAllRequestedBooks()
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

    suspend fun getRequestedBookById(id: UUID): Resource<RequestedBook> {
        val response = try {
            requestedBookService.getRequestedBookById(id)
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

    suspend fun getRequestedBookByISBN(isbn: String): Resource<RequestedBook> {
        val response = try {
            requestedBookService.getRequestedBookByISBN(isbn)
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

    suspend fun saveRequestedBook(bookIsbn: String): Resource<RequestedBook> {
        val response = try {
            requestedBookService.saveRequestedBook(bookIsbn)
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

    suspend fun deleteRequestedBook(bookIsbn: String): Resource<String> {
        val response = try {
            requestedBookService.deleteRequestedBook(bookIsbn)
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

    suspend fun changeBookStatus(
        requestedBookId: UUID,
        bookStatus: BookStatus
    ): Resource<RequestedBook> {
        val response = try {
            requestedBookService.changeBookStatus(requestedBookId, bookStatus)
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

    suspend fun handleRequestedBookLike(requestedBookId: UUID): Resource<RequestedBook> {
        val response = try {
            requestedBookService.handleRequestedBookLike(requestedBookId)
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

    suspend fun getRequestedBooksByBookStatus(status: BookStatus): Resource<List<RequestedBook>> {
        val response = try {
            requestedBookService.getRequestedBooksByBookStatus(status)
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