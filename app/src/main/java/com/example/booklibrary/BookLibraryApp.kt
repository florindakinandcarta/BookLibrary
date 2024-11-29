package com.example.booklibrary

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import io.scanbot.sdk.barcode_scanner.ScanbotBarcodeScannerSDKInitializer
import javax.inject.Inject

@HiltAndroidApp
class BookLibraryApp:Application(), Configuration.Provider {
    @Inject
    lateinit var hiltWorkerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        ScanbotBarcodeScannerSDKInitializer()
            .initialize(this)
    }
}