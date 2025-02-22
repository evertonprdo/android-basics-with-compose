package com.evertonprdo.flightsearch.data.repositories

import kotlinx.coroutines.flow.Flow

interface FlightSearchCacheRepository {
    val getCachedIataCode: Flow<String?>
    suspend fun cacheIataCode(iataCode: String?)
}