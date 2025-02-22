package com.evertonprdo.flightsearch.data.repositories

import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
    fun fetchFlights(iata: String): Flow<List<Flight>>
    fun getFavoriteFlights(): Flow<List<Flight>>
    suspend fun updateFavoriteFlight(flight: Flight)
}