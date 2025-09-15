package com.techcult.salesman.core.data.prefernces

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PreferenceManger(val preferences: DataStore<Preferences>) {

    companion object {

        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val LOGGED_ID = stringPreferencesKey("logged_id")
    }


    suspend fun getAccessToken(): Flow<String?> {
        return preferences.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }
    }

    suspend fun saveAccessToken(accessToken: String) {
        preferences.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }
    suspend fun getLoggedId(): Flow<String?> {
        return preferences.data.map { preferences ->
            preferences[LOGGED_ID]
        }
    }
    suspend fun saveLoggedId(loggedId: String) {
        preferences.edit { preferences ->
            preferences[LOGGED_ID] = loggedId
        }
    }

}