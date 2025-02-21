package com.evertonprdo.flightsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteFlightDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavoriteFlights(): Flow<List<FavoriteFlight>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favFlight: FavoriteFlight)

    @Query("DELETE FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode")
    suspend fun delete(departureCode: String, destinationCode: String)
}