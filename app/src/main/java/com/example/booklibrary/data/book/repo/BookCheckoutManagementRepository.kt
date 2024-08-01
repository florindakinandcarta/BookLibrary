package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.request.BookCheckoutRequest
import com.example.booklibrary.data.book.services.BookCheckoutManagementService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class BookCheckoutManagementRepository @Inject constructor(
    private val bookCheckoutManagementService: BookCheckoutManagementService
) {
    suspend fun borrowBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<String> {
        val response = try {
            bookCheckoutManagementService.borrowBookItem(bookCheckoutRequest)
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

    suspend fun returnBookItem(bookCheckoutRequest: BookCheckoutRequest): Resource<String> {
        val response = try {
            bookCheckoutManagementService.returnBookItem(bookCheckoutRequest)
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