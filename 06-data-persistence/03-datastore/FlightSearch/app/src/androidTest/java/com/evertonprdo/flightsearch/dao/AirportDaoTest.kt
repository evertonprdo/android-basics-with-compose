package com.evertonprdo.flightsearch.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evertonprdo.flightsearch.data.local.FlightDatabase
import com.evertonprdo.flightsearch.data.local.dao.AirportDao
import com.evertonprdo.flightsearch.database.RoomInMemoryDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AirportDaoTest {
    private lateinit var airportDao: AirportDao
    private lateinit var flightDatabase: FlightDatabase

    @Before
    fun createDb() {
        flightDatabase = RoomInMemoryDatabase.getDatabase()
        airportDao = flightDatabase.airportDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        flightDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoFetchAirports_fetchAirportsByIataCode() = runBlocking {
        val queryIataCodeResult = airportDao.fetchAirports("TS1").first()
        val hasExpectedIataCode =
            queryIataCodeResult.any { it.iataCode == "TS1" }

        assertTrue(hasExpectedIataCode)
    }

    @Test
    @Throws(Exception::class)
    fun daoFetchAirports_fetchAirportsByName() = runBlocking {
        val searchVal = "EsT"
        val queryNameResult = airportDao.fetchAirports(searchVal).first()

        val hasExpectedNames =
            queryNameResult.all {
                it.name.contains(
                    searchVal,
                    ignoreCase = true
                )
            }

        assertTrue(queryNameResult.isNotEmpty())
        assertTrue(hasExpectedNames)
    }

    @Test
    @Throws(Exception::class)
    fun daoFetchAirports_fetchAirportsOrderedByPassengers() {
        runBlocking {
            val airports = airportDao.fetchAirports("").first()

            assertTrue(airports.isNotEmpty())
            airports.zipWithNext { currAirport, nextAirport ->
                assertTrue(currAirport.passengers > nextAirport.passengers)
            }
        }
    }
}