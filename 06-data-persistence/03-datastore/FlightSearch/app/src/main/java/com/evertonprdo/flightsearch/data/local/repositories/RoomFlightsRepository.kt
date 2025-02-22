package com.evertonprdo.flightsearch.data.local.repositories

import com.evertonprdo.flightsearch.data.local.dao.FavoriteFlightDao
import com.evertonprdo.flightsearch.data.local.dao.FlightDao
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
import com.evertonprdo.flightsearch.data.local.repositories.mappers.FlightMapper
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomFlightsRepository(
    private val favoriteFlightDao: FavoriteFlightDao,
    private val flightDao: FlightDao
) : FlightsRepository {

    override suspend fun updateFavoriteFlight(flight: Flight) {
        if (flight.favorited) {
            return favoriteFlightDao.delete(
                departureCode = flight.depart.iata,
                destinationCode = flight.arrive.iata
            )
        }

        favoriteFlightDao.insert(
            FavoriteFlight(
                departureCode = flight.depart.iata,
                destinationCode = flight.arrive.iata
            )
        )
    }

    override fun fetchFlights(iata: String): Flow<List<Flight>> =
        flightDao.fetchFlights(iata).map {
            it.map(FlightMapper::toDomain)
        }

    override fun getFavoriteFlights(): Flow<List<Flight>> =
        flightDao.getAllFavoriteFlights().map {
            it.map(FlightMapper::toDomain)
        }
}
