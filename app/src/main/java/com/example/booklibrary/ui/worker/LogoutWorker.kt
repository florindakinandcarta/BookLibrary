package com.example.booklibrary.ui.worker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.booklibrary.util.saveUserJWTToken
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class LogoutWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters,
    private val dataStore: DataStore<Preferences>
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        saveUserJWTToken(dataStore, "")
        return Result.success()
    }
}