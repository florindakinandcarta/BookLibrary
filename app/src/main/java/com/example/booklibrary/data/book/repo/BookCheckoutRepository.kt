package com.example.booklibrary.data.book.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.models.request.BookReturnRequest
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutReturnReminderResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.services.BookCheckoutService
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class BookCheckoutRepository @Inject constructor(
    private val bookCheckoutService: BookCheckoutService,
    private val dataStore: DataStore<Preferences>
) {
    private val token: String by lazy {
        runBlocking {
            val jwtToken = getUserJWTToken(dataStore).first() ?: ""
            "Bearer $jwtToken"
        }
    }
    suspend fun getAllBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckouts(token)
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

    suspend fun getAllBookCheckoutsPaginated(
        numberOfPages: Int,
        pageSize: Int
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsPaginated(
                numberOfPages,
                pageSize
            )
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

    suspend fun getAllActiveBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllActiveBookCheckouts(token)
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

    suspend fun getAllPastBookCheckouts(): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllPastBookCheckouts(token)
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

    suspend fun getAllBookCheckoutsForBookTitle(
        titleSearchTerm: String
    ): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsForBookTitle(token, titleSearchTerm)
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

    suspend fun getAllBookCheckoutsForUser(): Resource<List<BookCheckoutResponse>> {
        val response = try {
            bookCheckoutService.getAllBookCheckoutsForUser(token)
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

    suspend fun getAllNearReturnDate(): Resource<List<BookCheckoutReturnReminderResponse>> {
        val response = try {
            bookCheckoutService.getAllNearReturnDate(token)
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
                errorResponse?.generalExceptionMessage ?: "Unknown Error"
            )
        } catch (e: Exception) {
            return Resource.Error(e.message.toString())
        }
        return Resource.Success(response)
    }

    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<BookCheckoutResponse> {
        val response = try {
            bookCheckoutService.borrowBookItem(token, bookCheckoutRequest)
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

    suspend fun returnBookItem(bookCheckoutRequest: BookReturnRequest): Resource<BookCheckoutResponse> {
        val response = try {
            bookCheckoutService.returnBookItem(token,bookCheckoutRequest)
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