package com.example.booklibrary.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream


fun convertBase64ToBitmap(profilePicture: String?): Bitmap? {
    return profilePicture?.let {
        try {
            val decodedBytes = Base64.decode(it, Base64.NO_WRAP)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            null
        }
    }
}

fun convertBitmapToString(pictureTaken: Bitmap): String? {
    try {
        val outputStream = ByteArrayOutputStream()
        pictureTaken.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}