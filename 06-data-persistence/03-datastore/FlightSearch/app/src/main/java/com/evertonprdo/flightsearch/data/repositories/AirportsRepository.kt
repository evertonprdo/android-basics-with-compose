package com.evertonprdo.flightsearch.data.repositories

import com.evertonprdo.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow

interface AirportsRepository {
    fun getAirport(iata: String): Flow<Airport>
    fun fetchAirports(query: String): Flow<List<Airport>>
}