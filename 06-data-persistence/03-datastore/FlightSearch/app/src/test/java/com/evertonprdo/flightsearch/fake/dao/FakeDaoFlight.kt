package com.evertonprdo.flightsearch.fake.dao

import com.evertonprdo.flightsearch.data.local.dao.FlightDao
import com.evertonprdo.flightsearch.data.local.dao.dto.FlightDto
import com.evertonprdo.flightsearch.fake.FakeDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDaoFlight(
    private val fakeDb: FakeDatabase
) : FlightDao {
    override fun fetchFlights(iata: String): Flow<List<FlightDto>> =
        flowOf(fakeDb.flights.filter { it.departureIataCode == iata })

    override fun getAllFavoriteFlights(): Flow<List<FlightDto>> =
        flowOf(fakeDb.flights.filter { it.favorited })
}