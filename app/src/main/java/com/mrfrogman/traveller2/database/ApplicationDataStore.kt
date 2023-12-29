package com.mrfrogman.traveller2.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ApplicationDataStore(
    private val context: Context,
    key: String,
){
    private val dataKey = stringPreferencesKey(key)
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Traveller")

    }
    val getData: Flow<String?> = context.dataStore.data
        .map {
            it[dataKey] ?: "null"
        }

    suspend fun saveData(data: String) {
        context.dataStore.edit {
            it[dataKey] = data
        }
    }
}