package com.evertonprdo.flightsearch.data

import android.content.Context
import com.evertonprdo.flightsearch.data.local.FlightDatabase
import com.evertonprdo.flightsearch.data.local.repositories.RoomAirportsRepository
import com.evertonprdo.flightsearch.data.local.repositories.RoomFlightsRepository
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository

interface AppContainer {
    val airportsRepository: AirportsRepository
    val flightsRepository: FlightsRepository
}

class AppDataContainer(context: Context) : AppContainer {
    private val database: FlightDatabase = FlightDatabase.getDatabase(context)

    override val flightsRepository: FlightsRepository by lazy {
        RoomFlightsRepository(
            airportsDao = database.airportDao(),
            favoriteFlightDao = database.favoriteFlightDao()
        )
    }

    override val airportsRepository: AirportsRepository by lazy {
        RoomAirportsRepository(database.airportDao())
    }
}