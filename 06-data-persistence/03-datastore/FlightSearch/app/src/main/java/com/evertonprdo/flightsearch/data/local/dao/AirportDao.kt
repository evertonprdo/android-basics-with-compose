package com.evertonprdo.flightsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.evertonprdo.flightsearch.data.local.entities.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query(
        """
        SELECT * FROM airport
        WHERE name LIKE '%' || :query || '%' COLLATE NOCASE
        OR iata_code LIKE :query || '%' COLLATE BINARY
        ORDER BY passengers DESC
        LIMIT 10
    """
    )
    fun fetchAirports(query: String): Flow<List<Airport>>

//    @Query("SELECT * FROM airport ORDER BY passengers")
//    fun getAllAirports(): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code = :iata")
    fun fetchAirport(iata: String): Flow<Airport>

//    @Query("SELECT * FROM airport WHERE id = :id")
//    fun fetchAirport(id: Int): Flow<Airport>
}
