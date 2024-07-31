package com.example.booklibrary.data.book.models.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class BookCheckoutRequest(
    val userId: UUID,
    val bookItemId: UUID
):Parcelable
