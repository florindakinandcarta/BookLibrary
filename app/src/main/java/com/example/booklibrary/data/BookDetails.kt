package com.example.booklibrary.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDetails(
    val ISBN: String? = null,
    val title: String? = null,
    val description: String? = null,
    val bookStatus: BookState? = null,
    val webRating: Double? = null,
    val companyRating: Double? = null,
    val languages: Languages? = null,
    val totalPages: Int? = null,
    val genre: List<Genres>? = null,
    val author: String? = null
) : Parcelable
