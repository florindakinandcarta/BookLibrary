package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.BookID
import com.example.booklibrary.data.book.models.BookItem
import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.services.BookItemService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class BookItemRepository @Inject constructor(
    private val bookItemService: BookItemService
) {
    suspend fun getBookItemsByBookIsbn(isbn: String, officeName:String): Resource<List<BookItem>> {
        val response = try {
            bookItemService.getBookItemsByBookIsbn(isbn,officeName)
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

    suspend fun saveBookItem(bookID: BookID): Resource<BookItem> {
        val response = try {
            bookItemService.saveBookItem(bookID)
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

    suspend fun deleteBookItem(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.deleteBookItem(id)
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

    suspend fun reportBookItemAsDamaged(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.reportBookItemAsDamaged(id)
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

    suspend fun reportBookItemAsLost(id: UUID): Resource<UUID> {
        val response = try {
            bookItemService.reportBookItemAsLost(id)
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