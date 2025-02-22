package com.evertonprdo.flightsearch.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.evertonprdo.flightsearch.data.local.dao.dto.FlightDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query( // Query by chatGPT
        """
        SELECT 
            dep.id AS departureId, 
            dep.name AS departureName, 
            dep.passengers AS departurePassengers, 
            dep.iata_code AS departureIataCode,
            
            dest.id AS destinationId,
            dest.name AS destinationName, 
            dest.passengers AS destinationPassengers, 
            dest.iata_code AS destinationIataCode,
            
            CASE 
                WHEN f.id IS NOT NULL THEN 1
                ELSE 0
            END AS favorited

        FROM airport AS dep
        CROSS JOIN airport AS dest
        LEFT JOIN favorite AS f 
        ON dep.iata_code = f.departure_code 
        AND dest.iata_code = f.destination_code
        WHERE dep.iata_code = :iata
        AND dep.id != dest.id
        
        ORDER BY dest.passengers DESC
    """
    )
    fun fetchFlights(iata: String): Flow<List<FlightDto>>

    @Query( // Query by chatGPT
        """
        SELECT 
            dep.id AS departureId, 
            dep.name AS departureName, 
            dep.passengers AS departurePassengers, 
            dep.iata_code AS departureIataCode,
            
            dest.id AS destinationId, 
            dest.name AS destinationName, 
            dest.passengers AS destinationPassengers, 
            dest.iata_code AS destinationIataCode,
            
            CASE 
                WHEN f.id IS NOT NULL THEN 1 
                ELSE 0 
            END AS favorited

        FROM favorite AS f
        INNER JOIN airport AS dep ON f.departure_code = dep.iata_code
        INNER JOIN airport AS dest ON f.destination_code = dest.iata_code
        
        ORDER BY dest.passengers DESC
    """
    )
    fun getAllFavoriteFlights(): Flow<List<FlightDto>>
}