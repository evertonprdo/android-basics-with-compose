package com.evertonprdo.flightsearch.data.local.repositories

import com.evertonprdo.flightsearch.data.local.dao.AirportDao
import com.evertonprdo.flightsearch.data.local.repositories.mappers.AirportMapper
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.model.Airport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomAirportsRepository(
    private val airportDao: AirportDao
) : AirportsRepository {

    override fun fetchAirports(query: String): Flow<List<Airport>> =
        airportDao.fetchAirports(query)
            .map { it.map(AirportMapper::toDomain) }

    override fun getAirport(iata: String): Flow<Airport> =
        airportDao.fetchAirport(iata)
            .map(AirportMapper::toDomain)
}