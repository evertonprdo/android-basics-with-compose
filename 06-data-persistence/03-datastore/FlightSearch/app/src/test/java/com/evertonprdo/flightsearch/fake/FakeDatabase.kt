package com.evertonprdo.flightsearch.fake

import com.evertonprdo.flightsearch.data.local.dao.dto.FlightDto
import com.evertonprdo.flightsearch.data.local.entities.Airport
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight

class FakeDatabase {
    val airports = listOf(
        Airport(
            id = 1,
            name = "test-01",
            iataCode = "TS1",
            passengers = 1000
        ),
        Airport(
            id = 2,
            name = "test-02",
            iataCode = "TS2",
            passengers = 2000
        ),
        Airport(
            id = 3,
            name = "test-03",
            iataCode = "TS3",
            passengers = 3000
        ),
    )

    val favorites = mutableListOf(
        FavoriteFlight(
            id = 1,
            departureCode = "TS1",
            destinationCode = "TS2"
        )
    )

    val flights: MutableList<FlightDto>
        get() = makeFlights(airports)

    private fun makeFlights(airports: List<Airport>): MutableList<FlightDto> {
        val list: MutableList<FlightDto> = mutableListOf()

        for (i in 0..airports.lastIndex) {
            for (j in 0..airports.lastIndex) {
                if (i == j)
                    continue

                list.add(makeFlight(airports[i], airports[j]))
            }
        }

        return list
    }

    private fun makeFlight(
        depart: Airport,
        arrive: Airport
    ): FlightDto =
        FlightDto(
            departureId = depart.id,
            departureName = depart.name,
            departureIataCode = depart.iataCode,
            departurePassengers = depart.passengers,

            destinationId = arrive.id,
            destinationName = arrive.name,
            destinationIataCode = arrive.iataCode,
            destinationPassengers = arrive.passengers,

            favorited = favorites.any {
                it.departureCode == depart.iataCode && it.destinationCode == arrive.iataCode
            }
        )
}
