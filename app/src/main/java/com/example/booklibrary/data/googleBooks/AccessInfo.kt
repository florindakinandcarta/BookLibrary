package com.example.booklibrary.data.googleBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccessInfo(
    val country: String? = null,
    val viewability: String? = null,
    val embeddable: Boolean? = null,
    val publicDomain: Boolean? = null,
    val textToSpeechPermission: String? = null,
    val epub: Epub? = Epub(),
    val pdf: Pdf? = Pdf(),
    val webReaderLink: String? = null,
    val accessViewStatus: String? = null,
    val quoteSharingAllowed: Boolean? = null
) : Parcelable