package com.evertonprdo.flightsearch.repositories

import com.evertonprdo.flightsearch.data.local.repositories.RoomAirportsRepository
import com.evertonprdo.flightsearch.data.local.repositories.mappers.AirportMapper
import com.evertonprdo.flightsearch.fake.FakeDatabase
import com.evertonprdo.flightsearch.fake.dao.FakeDaoAirport
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RoomAirportsRepositoryTest {
    private val fakeDb = FakeDatabase()
    private val repository =
        RoomAirportsRepository(airportDao = FakeDaoAirport(fakeDb))

    @Test
    fun roomAirportsRepository_fetchAirports() = runTest {
        assertEquals(
            fakeDb.airports.map(AirportMapper::toDomain),
            repository.fetchAirports("").first()
        )
    }

    @Test
    fun roomAirportsRepository_getAirport() = runTest {
        assertEquals(
            AirportMapper.toDomain(fakeDb.airports[0]),
            repository.getAirport("TS1").first()
        )
    }
}