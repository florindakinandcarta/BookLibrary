package com.example.booklibrary.data.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewRequest(
    val bookISBN: String,
    val userEmail: String,
    val message: String,
    val rating: Int? = null
) : Parcelable {
    init {
        require(isRatingValid(rating)) {
            "Rating must be between $MIN_RATING and $MAX_RATING"
        }
    }

    companion object {
        private const val MIN_RATING = 1
        private const val MAX_RATING = 5

        fun isRatingValid(value: Int?): Boolean {
            return value in MIN_RATING..MAX_RATING
        }
    }
}
