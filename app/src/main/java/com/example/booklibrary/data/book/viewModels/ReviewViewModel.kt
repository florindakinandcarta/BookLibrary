package com.example.booklibrary.data.book.viewModels

import androidx.lifecycle.ViewModel
import com.example.booklibrary.data.book.models.request.ReviewRequest
import com.example.booklibrary.data.book.models.response.ReviewResponse
import com.example.booklibrary.data.book.repo.ReviewRepository
import com.example.booklibrary.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository
) : ViewModel() {
    suspend fun getAllReviewsByBookISBN(
        bookISBN: String,
        officeName: String
    ): Resource<List<ReviewResponse>> {
        return reviewRepository.getAllReviewsByBookISBN(bookISBN, officeName)
    }

    suspend fun getTopReviewsForDisplayInBookView(
        bookISBN: String,
        officeName: String
    ): Resource<List<ReviewResponse>> {
        return reviewRepository.getTopReviewsForDisplayInBookView(bookISBN, officeName)
    }

    suspend fun insertReview(
        reviewInsert: ReviewRequest
    ): Resource<ReviewResponse> {
        return reviewRepository.insertReview(reviewInsert)
    }


    suspend fun updateReview(
        reviewUpdate: ReviewRequest
    ): Resource<ReviewResponse> {
        return reviewRepository.updateReview(reviewUpdate)
    }

    suspend fun deleteReviewById(id: UUID): Resource<UUID> {
        return reviewRepository.deleteReviewById(id)
    }

    suspend fun getReviewByUserId(id: UUID): Resource<ReviewResponse> {
        return reviewRepository.getReviewByUserId(id)
    }
}