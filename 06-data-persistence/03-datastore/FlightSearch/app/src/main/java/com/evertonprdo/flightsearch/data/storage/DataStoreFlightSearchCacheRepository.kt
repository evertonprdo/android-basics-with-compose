package com.evertonprdo.flightsearch.data.storage

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.evertonprdo.flightsearch.data.repositories.FlightSearchCacheRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreFlightSearchCacheRepository(
    private val dataStore: DataStore<Preferences>
) : FlightSearchCacheRepository {

    private companion object {
        val CACHED_AIRPORT = stringPreferencesKey("cached_airport")
        const val TAG = "CacheRepo"
    }

    override val airportIataCode: Flow<String?> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[CACHED_AIRPORT]
        }

    override suspend fun cacheIataCode(iataCode: String?) {
        dataStore.edit { preferences ->
            if (iataCode == null)
                preferences.remove(CACHED_AIRPORT)
            else
                preferences[CACHED_AIRPORT] = iataCode
        }
    }
}