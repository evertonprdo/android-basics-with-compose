package com.evertonprdo.flightsearch.data.local.repositories.mappers

import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.data.local.entities.Airport as RoomAirport

object AirportMapper {
    fun toDomain(roomAirport: RoomAirport): Airport = roomAirport.let {
        Airport(
            id = it.id,
            name = it.name,
            iata = it.iataCode,
        )
    }
}