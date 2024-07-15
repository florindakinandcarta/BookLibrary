package com.example.booklibrary

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookLibraryApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}