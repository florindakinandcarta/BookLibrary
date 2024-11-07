package com.example.booklibrary.data.book.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Genres(val displayName: String) : Parcelable {
    AVAILABLE("Available"),
    BIOGRAPHY_AUTOBIOGRAPHY("Biography & Autobiography"),
    COMPUTERS("Computers"),
    EDUCATION("Education"),
    FAMILY_RELATIONSHIPS("Family & Relationships"),
    FICTION("Fiction"),
    RELIGION("Religion"),
    BOOK_CLUBS("Book clubs "),
    CHILDREN_STORIES("Children's stories"),
    FAMILIES("Families"),
    JUVENILE_FICTION("Juvenile Fiction");


    override fun toString(): String {
        return displayName
    }
}
