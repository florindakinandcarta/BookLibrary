package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.response.OfficeResponse
import com.example.booklibrary.data.book.services.OfficeService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import javax.inject.Inject

class OfficeRepository @Inject constructor(
    private val officeService: OfficeService
) {
    suspend fun getAllOffices(): Resource<List<OfficeResponse>> {
        val response = try {
            officeService.getAllOffices()
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