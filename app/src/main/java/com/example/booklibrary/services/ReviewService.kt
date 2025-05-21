package com.example.booklibrary.services

import com.example.booklibrary.data.models.request.ReviewRequest
import com.example.booklibrary.data.models.response.ReviewResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ReviewService {

    @GET("reviews/{reviewId}")
    suspend fun getReviewByUserId(
        @Path("reviewId") reviewId: UUID
    ): ReviewResponse

    @GET("reviews")
    suspend fun getAllReviewsByBookISBN(
        @Query("bookISBN") bookISBN: String
    ): List<ReviewResponse>

    @GET("reviews/top-reviews")
    suspend fun getTopReviewsForDisplayInBookView(
        @Query("bookISBN") bookISBN: String
    ): List<ReviewResponse>

    @POST("reviews/insert")
    suspend fun insertReview(
        @Body reviewInsert: ReviewRequest
    ): ReviewResponse

    @PUT("reviews/update")
    suspend fun updateReview(
        @Body reviewRequest: ReviewRequest
    ): ReviewResponse

    @DELETE("reviews/delete/{reviewId} ")
    suspend fun deleteReviewById(
        @Path("reviewId") reviewId: UUID
    ): UUID
}