package com.example.booklibrary.data.book.services

import com.example.booklibrary.data.book.models.request.ReviewRequest
import com.example.booklibrary.data.book.models.response.ReviewResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ReviewService {

    @GET("review-query/{reviewId}/display")
    suspend fun getReviewByUserId(
        @Path("reviewId") reviewId: UUID
    ): ReviewResponse

    @GET("review-query/{bookISBN}/reviews")
    suspend fun getAllReviewsByBookISBN(
        @Path("bookISBN") bookISBN: String,
        @Query("officeName") officeName: String
    ): List<ReviewResponse>

    @GET("/review-query/{bookISBN}/top-reviews")
    suspend fun getTopReviewsForDisplayInBookView(
        @Path("bookISBN") bookISBN: String,
        @Query("officeName") officeName: String
    ): List<ReviewResponse>

    @POST("review-management/{bookISBN}/new-review")
    suspend fun insertReview(
        @Path("bookISBN") bookISBN: String,
        @Body reviewInsert: ReviewRequest
    ): ReviewResponse

    @PUT("review-management/{reviewId}/update")
    suspend fun updateReview(
        @Path("reviewId") reviewId: UUID,
        @Body reviewRequest: ReviewRequest
    ): ReviewResponse

    @DELETE("/review-management/{reviewId}/delete ")
    suspend fun deleteReviewById(
        @Path("reviewId") reviewId: UUID
    ): UUID
}