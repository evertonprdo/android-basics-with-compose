package com.evertonprdo.flightsearch.fake.dao

import com.evertonprdo.flightsearch.data.local.dao.FavoriteFlightDao
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
import com.evertonprdo.flightsearch.fake.FakeDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDaoFavoriteFlight(
    private val fakeDb: FakeDatabase
) : FavoriteFlightDao {

    override fun getAllFavoriteFlights(): Flow<List<FavoriteFlight>> {
        return flowOf(fakeDb.favorites)
    }

    override suspend fun insert(favFlight: FavoriteFlight) {
        fakeDb.favorites.add(favFlight)
    }

    override suspend fun delete(
        departureCode: String,
        destinationCode: String
    ) {
        fakeDb.favorites.removeIf {
            it.departureCode == departureCode && it.destinationCode == destinationCode
        }
    }
}