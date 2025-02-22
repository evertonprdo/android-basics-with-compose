package com.evertonprdo.flightsearch.repositories

import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
import com.evertonprdo.flightsearch.data.local.repositories.RoomFlightsRepository
import com.evertonprdo.flightsearch.fake.FakeDatabase
import com.evertonprdo.flightsearch.fake.dao.FakeDaoFavoriteFlight
import com.evertonprdo.flightsearch.fake.dao.FakeDaoFlight
import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RoomFlightsRepositoryTest {
    private val fakeDb = FakeDatabase()
    private val favoriteFlightDao = FakeDaoFavoriteFlight(fakeDb)

    private val repository = RoomFlightsRepository(
        flightDao = FakeDaoFlight(fakeDb),
        favoriteFlightDao = favoriteFlightDao,
    )

    @Test
    fun roomFlightsRepository_FetchFlightsByIataCode() = runTest {
        val departIataCode = "TS1"
        val flights = repository.fetchFlights(departIataCode).first()

        assertTrue(flights.isNotEmpty())

        for (flight in flights) {
            assertTrue(flight.depart.iata == departIataCode)
            assertFalse(flight.arrive.iata == departIataCode)
        }
    }

    @Test
    fun roomFlightsRepository_getFavoriteFlights() = runTest {
        val flights = repository.getFavoriteFlights().first()

        assertTrue(flights.isNotEmpty())
        assertTrue(flights[0].favorited)
    }

    @Test
    fun roomFlightsRepository_updateFavoriteFlight_favorite() = runTest {
        val flights = repository.fetchFlights("TS2").first()
        val favorite = flights[0]

        assertFalse(favorite.favorited)
        repository.updateFavoriteFlight(favorite)

        val fakeDbFlight = getFavoriteFlight(favorite)
        assertNotNull(fakeDbFlight)
    }

    @Test
    fun roomFlightsRepository_updateFavoriteFlight_unfavorite() = runTest {
        val flights = repository.fetchFlights("TS2").first()
        var favorite = flights[0]

        assertFalse(favorite.favorited)
        repository.updateFavoriteFlight(favorite)

        var fakeDbFlight = getFavoriteFlight(favorite)
        assertNotNull(fakeDbFlight)

        favorite = favorite.copy(favorited = true)
        repository.updateFavoriteFlight(favorite)

        fakeDbFlight = getFavoriteFlight(favorite)
        assertNull(fakeDbFlight)
    }

    private fun getFavoriteFlight(flight: Flight): FavoriteFlight? {
        fakeDb.favorites.forEach {
            println(it)
        }
        return fakeDb.favorites.find {
            flight.depart.iata == it.departureCode && flight.arrive.iata == it.destinationCode
        }
    }
}