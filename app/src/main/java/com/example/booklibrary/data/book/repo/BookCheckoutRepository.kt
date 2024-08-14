package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.services.BookCheckoutService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class BookCheckoutRepository @Inject constructor(
    private val bookCheckoutService: BookCheckoutService
) {
    suspend fun getAllBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckouts(officeName)
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

    suspend fun getAllBookCheckoutsPaginated(
        officeName: String,
        numberOfPages: Int,
        pageSize: Int
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsPaginated(
                officeName,
                numberOfPages,
                pageSize
            )
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

    suspend fun getAllActiveBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllActiveBookCheckouts(officeName)
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

    suspend fun getAllPastBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllPastBookCheckouts(officeName)
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

    suspend fun getAllBookCheckoutsForBookTitle(
        officeName: String,
        titleSearchTerm: String
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsForBookTitle(officeName, titleSearchTerm)
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

    suspend fun getAllBookCheckoutsFromUserWithId(userId: UUID): Resource<List<BookCheckoutResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsForUserWithId(userId)
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

    suspend fun getAllNearReturnDate(officeName: String): Resource<List<BookCheckoutReturnReminderResponse>> {
        val response = try {
            bookCheckoutService.getAllNearReturnDate(officeName)
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

    suspend fun getAllBooksForUserByTitleContaining(
        userId: UUID,
        titleSearchTerm: String
    ): Resource<List<BookCheckoutResponse>> {
        val response = try {
            bookCheckoutService.getAllBooksForUserByTitleContaining(userId, titleSearchTerm)
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

    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        val response = try {
            bookCheckoutService.borrowBookItem(bookCheckoutRequest)
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

    suspend fun returnBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        val response = try {
            bookCheckoutService.returnBookItem(bookCheckoutRequest)
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