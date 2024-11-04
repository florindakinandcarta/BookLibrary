package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class BookStatus : Parcelable {
    REQUESTED,
    PENDING_PURCHASE,
    REJECTED,
    IN_STOCK
}
val BookStatus.displayName: String
    get() = when (this) {
        BookStatus.REQUESTED -> "Requested"
        BookStatus.PENDING_PURCHASE -> "Pending Purchase"
        BookStatus.REJECTED -> "Rejected"
        BookStatus.IN_STOCK -> "In Stock"
    }