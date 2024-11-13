package com.example.booklibrary.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64


fun convertBase64ToBitmap(profilePicture: String?): Bitmap? {
    return profilePicture?.let {
        val decodedBytes = Base64.decode(it, it.length)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }
}