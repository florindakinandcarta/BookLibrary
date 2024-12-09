package com.example.booklibrary.data.book.repo

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookItem
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.services.BookItemService
import com.example.booklibrary.util.Resource
import com.example.booklibrary.util.getUserJWTToken
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class BookItemRepository @Inject constructor(
    private val bookItemService: BookItemService,
    private val dataStore: DataStore<Preferences>
) {
    private val token: String by lazy {
        runBlocking {
            val jwtToken = getUserJWTToken(dataStore).first() ?: ""
            "Bearer $jwtToken"
        }
    }
    suspend fun getBookItemsByBookIsbn(isbn: String): Resource<List<BookItem>> {
        val response = try {
            bookItemService.getBookItemsByBookIsbn(token, isbn)
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

    suspend fun createBookItem(bookISBN: BookID): Resource<BookItem> {
        val response = try {
            bookItemService.createBookItem(token, bookISBN)
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

    suspend fun deleteBookItem(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.deleteBookItem(id)
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

    suspend fun reportBookItemAsDamaged(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.reportBookItemAsDamaged(id)
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

    suspend fun reportBookItemAsLost(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.reportBookItemAsLost(id)
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