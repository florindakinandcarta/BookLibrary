package com.example.booklibrary.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

val USER_JWT_TOKEN = stringPreferencesKey("user_jwt_token")
val USER_ROLE = booleanPreferencesKey("user_role")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }
}

suspend fun saveUserJWTToken(dataStore: DataStore<Preferences>, userJWTToken: String) {
    dataStore.edit { preferences ->
        preferences[USER_JWT_TOKEN] = userJWTToken
    }
}

suspend fun saveUserRole(dataStore: DataStore<Preferences>, userRole: Boolean) {
    dataStore.edit { preferences ->
        preferences[USER_ROLE] = userRole
    }
}

fun getUserRole(dataStore: DataStore<Preferences>): Flow<Boolean> {
    return dataStore.data
        .map { preferences ->
            preferences[USER_ROLE] ?: false
        }
}

fun getUserJWTToken(dataStore: DataStore<Preferences>): Flow<String?> {
    return dataStore.data
        .map { preferences ->
            preferences[USER_JWT_TOKEN] ?: ""
        }
}