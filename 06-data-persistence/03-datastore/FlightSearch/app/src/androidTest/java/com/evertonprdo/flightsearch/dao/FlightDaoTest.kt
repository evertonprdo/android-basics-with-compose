package com.evertonprdo.flightsearch.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evertonprdo.flightsearch.data.local.FlightDatabase
import com.evertonprdo.flightsearch.data.local.dao.FlightDao
import com.evertonprdo.flightsearch.database.RoomInMemoryDatabase
import com.evertonprdo.flightsearch.database.RoomInMemoryDatabase.seedFavoriteFlights
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FlightDaoTest {
    private lateinit var flightDao: FlightDao
    private lateinit var flightDatabase: FlightDatabase

    @Before
    fun createDb() {
        flightDatabase = RoomInMemoryDatabase.getDatabase()
        flightDao = flightDatabase.flightDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        flightDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoFetchFlights_fetchFlightsByIataCode() = runBlocking {
        val queryIataCode = "TS1"
        val flights = flightDao.fetchFlights(queryIataCode).first()

        assertTrue(flights.isNotEmpty())
        for (flight in flights) {
            assertTrue(flight.departureIataCode == queryIataCode)
            assertFalse(flight.destinationIataCode == queryIataCode)
        }
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllFavoriteFlights_getAllFavoriteFlights() = runBlocking {
        seedFavoriteFlights(flightDatabase)

        val favorites = flightDao.getAllFavoriteFlights().first()
        assertTrue(favorites.isNotEmpty())
    }
}