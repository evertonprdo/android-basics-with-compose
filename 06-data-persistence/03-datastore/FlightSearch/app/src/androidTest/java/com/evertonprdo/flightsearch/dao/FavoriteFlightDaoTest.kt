package com.evertonprdo.flightsearch.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.evertonprdo.flightsearch.data.local.FlightDatabase
import com.evertonprdo.flightsearch.data.local.dao.FavoriteFlightDao
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
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
class FavoriteFlightDaoTest {
    private lateinit var favoriteDao: FavoriteFlightDao
    private lateinit var flightDatabase: FlightDatabase

    private var favoriteFlight = FavoriteFlight(
        departureCode = "TS1",
        destinationCode = "TS2"
    )

    @Before
    fun createDb() {
        flightDatabase = RoomInMemoryDatabase.getDatabase()
        favoriteDao = flightDatabase.favoriteFlightDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        flightDatabase.close()
    }

    private suspend fun insertFavorite() {
        favoriteDao.insert(favoriteFlight)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertFavoriteFlight() = runBlocking {
        insertFavorite()

        val favorites = favoriteDao.getAllFavoriteFlights().first()
        assertEquals(favorites.size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDelete_deleteFavoriteFlight() = runBlocking {
        insertFavorite()
        favoriteDao.delete(
            departureCode = favoriteFlight.departureCode,
            destinationCode = favoriteFlight.destinationCode
        )

        val favorites = favoriteDao.getAllFavoriteFlights().first()
        assertTrue(favorites.isEmpty())
    }
}