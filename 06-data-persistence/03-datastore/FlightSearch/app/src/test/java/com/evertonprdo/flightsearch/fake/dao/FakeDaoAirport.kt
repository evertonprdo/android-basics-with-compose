package com.evertonprdo.flightsearch.fake.dao

import com.evertonprdo.flightsearch.data.local.dao.AirportDao
import com.evertonprdo.flightsearch.data.local.entities.Airport
import com.evertonprdo.flightsearch.fake.FakeDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDaoAirport(
    private val fakeDb: FakeDatabase
) : AirportDao {
    override fun fetchAirports(query: String): Flow<List<Airport>> =
        flowOf(fakeDb.airports.fetch(query))

    override fun fetchAirport(iata: String): Flow<Airport> =
        flowOf(fakeDb.airports.first { it.iataCode == iata })
}

private fun List<Airport>.fetch(query: String): List<Airport> =
    this.filter {
        it.name.contains(query, true)
                || it.iataCode.contains(query, true)
    }
