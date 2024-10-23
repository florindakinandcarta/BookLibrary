package com.example.booklibrary.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

val USER_JWT_TOKEN = stringPreferencesKey("user_jwt_token")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(context: Context): DataStore<Preferences>{
        return context.dataStore
    }
}

suspend fun saveUserJWTToken(dataStore: DataStore<Preferences>, userJWTToken: String) {
    dataStore.edit { preferences ->
        preferences[USER_JWT_TOKEN] = userJWTToken
    }
}

fun getUserJWTToken(dataStore: DataStore<Preferences>): Flow<String?> {
    return dataStore.data
        .map { preferences ->
            preferences[USER_JWT_TOKEN] ?: ""
        }
}