package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.ReviewRequest
import com.example.booklibrary.data.book.models.response.ReviewResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.UUID

interface ReviewService {

    @GET("review/{id}")
    suspend fun getReviewByUserId(
        @Path("id") id: UUID
    ): ReviewResponse

    @GET("review/{isbn}")
    suspend fun getAllReviewsByBookISBN(
        @Path("isbn") isbn: String
    ): List<ReviewResponse>

    @GET("review/topReviews/{isbn}")
    suspend fun getTopReviewsForDisplayInBookView(
        @Path("isbn") isbn: String
    ): List<ReviewResponse>

    @POST("review/new")
    suspend fun insertReview(
        @Body reviewInsert: ReviewRequest
    ): ReviewResponse

    @PUT("review/{reviewId}/update")
    suspend fun updateReview(
        @Body reviewRequest: ReviewRequest
    ): ReviewResponse

    @DELETE("review/{id}/delete")
    suspend fun deleteReviewById(
        @Path("id") id: UUID
    ): UUID
}