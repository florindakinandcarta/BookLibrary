package com.example.booklibrary.data.book.repo

import com.example.booklibrary.data.book.models.ExceptionResponse
import com.example.booklibrary.data.book.models.request.ReviewRequest
import com.example.booklibrary.data.book.models.response.ReviewResponse
import com.example.booklibrary.data.book.services.ReviewService
import com.example.booklibrary.util.Resource
import com.google.gson.Gson
import retrofit2.HttpException
import java.util.UUID
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewService: ReviewService
) {
    suspend fun getAllReviewsByBookISBN(
        bookISBN: String,
        officeName: String
    ): Resource<List<ReviewResponse>> {
        val response = try {
            reviewService.getAllReviewsByBookISBN(bookISBN, officeName)
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

    suspend fun getTopReviewsForDisplayInBookView(
        bookISBN: String,
        officeName: String
    ): Resource<List<ReviewResponse>> {
        val response = try {
            reviewService.getTopReviewsForDisplayInBookView(bookISBN, officeName)
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

    suspend fun insertReview(
        reviewInsert: ReviewRequest
    ): Resource<ReviewResponse> {
        val response = try {
            reviewService.insertReview(reviewInsert)
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

    suspend fun updateReview(
        reviewUpdate: ReviewRequest
    ): Resource<ReviewResponse> {
        val response = try {
            reviewService.updateReview(reviewUpdate)
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

    suspend fun deleteReviewById(id: UUID): Resource<UUID> {
        val response = try {
            reviewService.deleteReviewById(id)
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

    suspend fun getReviewByUserId(id: UUID): Resource<ReviewResponse> {
        val response = try {
            reviewService.getReviewByUserId(id)
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