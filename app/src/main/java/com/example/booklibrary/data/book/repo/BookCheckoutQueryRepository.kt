package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutResponse
import com.example.booklibrary.data.book.models.response.BookCheckoutWithUserAndBookItemResponse
import com.example.booklibrary.data.book.services.BookCheckoutQueryService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class BookCheckoutQueryRepository @Inject constructor(
    private val bookCheckoutQueryService: BookCheckoutQueryService
) {
    suspend fun getAllBookCheckouts(officeName: String): Resource<List<BookCheckoutWithUserAndBookItemResponse>> {
        val response = try {
            bookCheckoutQueryService.getAllBookCheckouts(officeName)
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
            bookCheckoutQueryService.getAllBookCheckoutsPaginated(
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
            bookCheckoutQueryService.getAllActiveBookCheckouts(officeName)
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
            bookCheckoutQueryService.getAllPastBookCheckouts(officeName)
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
            bookCheckoutQueryService.getAllBookCheckoutsForBookTitle(officeName, titleSearchTerm)
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
            bookCheckoutQueryService.getAllBookCheckoutsFromUserWithId(userId)
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