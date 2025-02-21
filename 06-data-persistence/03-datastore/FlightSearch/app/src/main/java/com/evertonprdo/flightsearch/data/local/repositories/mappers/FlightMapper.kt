package com.evertonprdo.flightsearch.data.local.repositories.mappers

import com.evertonprdo.flightsearch.data.local.dao.dto.FlightDto
import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.model.Flight

object FlightMapper {
    fun toDomain(flightDto: FlightDto): Flight = flightDto.let {
        val depart = Airport(
            id = it.departureId,
            name = it.departureName,
            iata = it.departureIataCode
        )

        val arrive = Airport(
            id = it.destinationId,
            name = it.destinationName,
            iata = it.destinationIataCode
        )

        Flight(
            depart = depart,
            arrive = arrive,
            favorited = it.favorited
        )
    }
}