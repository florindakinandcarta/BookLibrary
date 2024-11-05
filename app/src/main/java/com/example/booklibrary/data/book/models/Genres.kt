package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Genres(val displayName: String) : Parcelable {
    BIOGRAPHY_AUTOBIOGRAPHY("Biography & Autobiography"),
    COMPUTERS("Computers"),
    EDUCATION("Education"),
    FAMILY_RELATIONSHIPS("Family & Relationships"),
    FICTION("Fiction"),
    RELIGION("Religion");

    override fun toString(): String {
        return displayName
    }
}
