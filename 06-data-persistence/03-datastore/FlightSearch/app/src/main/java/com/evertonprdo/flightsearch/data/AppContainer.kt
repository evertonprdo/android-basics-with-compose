package com.evertonprdo.flightsearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.evertonprdo.flightsearch.data.local.FlightDatabase
import com.evertonprdo.flightsearch.data.local.repositories.RoomAirportsRepository
import com.evertonprdo.flightsearch.data.local.repositories.RoomFlightsRepository
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.data.repositories.FlightSearchCacheRepository
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.data.storage.DataStoreFlightSearchCacheRepository

interface AppContainer {
    val airportsRepository: AirportsRepository
    val flightsRepository: FlightsRepository
    val flightSearchCacheRepository: FlightSearchCacheRepository
}

class AppDataContainer(context: Context) : AppContainer {
    private val database: FlightDatabase = FlightDatabase.getDatabase(context)

    override val flightsRepository: FlightsRepository by lazy {
        RoomFlightsRepository(
            flightDao = database.flightDao(),
            favoriteFlightDao = database.favoriteFlightDao()
        )
    }

    override val flightSearchCacheRepository: FlightSearchCacheRepository by lazy {
        DataStoreFlightSearchCacheRepository(context.dataStore)
    }

    override val airportsRepository: AirportsRepository by lazy {
        RoomAirportsRepository(database.airportDao())
    }
}

private const val FLIGHT_SEARCH_CACHE_NAME = "flight_search_cache"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = FLIGHT_SEARCH_CACHE_NAME
)