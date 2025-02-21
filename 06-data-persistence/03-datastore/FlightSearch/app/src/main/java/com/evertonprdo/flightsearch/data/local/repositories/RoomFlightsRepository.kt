package com.evertonprdo.flightsearch.data.local.repositories

import com.evertonprdo.flightsearch.data.local.dao.AirportDao
import com.evertonprdo.flightsearch.data.local.dao.FavoriteFlightDao
import com.evertonprdo.flightsearch.data.local.entities.Airport
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight
import com.evertonprdo.flightsearch.data.local.repositories.mappers.AirportMapper
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class RoomFlightsRepository(
    private val airportsDao: AirportDao,
    private val favoriteFlightDao: FavoriteFlightDao
) : FlightsRepository {

    override suspend fun updateFavoriteFlight(flight: Flight) {
        if (flight.favorited) {
            return favoriteFlightDao.delete(
                flight.depart.iata,
                flight.arrive.iata
            )
        }

        favoriteFlightDao.insert(
            FavoriteFlight(
                departureCode = flight.depart.iata,
                destinationCode = flight.arrive.iata
            )
        )
    }

    override suspend fun fetchFlights(iata: String): Flow<List<Flight>> =
        coroutineScope {
            val airportDeffer =
                async { airportsDao.fetchAirport(iata).first() }

            val favoriteFlightsDeffer =
                async { favoriteFlightDao.getAllFavoriteFlights().first() }

            val departAirport = airportDeffer.await()
            val favoriteFlights = favoriteFlightsDeffer.await()

            airportsDao.getAllAirports().map { airports ->
                airports
                    .filter { departAirport.iataCode != iata }
                    .map {
                        Flight(
                            depart = AirportMapper.toDomain(departAirport),
                            arrive = AirportMapper.toDomain(it),
                            favorited = favoriteFlights.hasFavoriteFlight(
                                departAirport,
                                it
                            )
                        )
                    }
            }
        }

    override fun fetchFavoriteFlights(): Flow<List<Flight>> {
        return favoriteFlightDao.getAllFavoriteFlights().map {
            coroutineScope {
                it.map { favorite ->
                    async { mapFavoriteFlights(favorite) }
                }.awaitAll()
            }
        }
    }

    private suspend fun mapFavoriteFlights(favorite: FavoriteFlight): Flight =
        coroutineScope {
            val departDeffer = async {
                airportsDao.fetchAirport(favorite.departureCode).first()
            }
            val arriveDeffer = async {
                airportsDao.fetchAirport(favorite.destinationCode).first()
            }

            val departureAirport = departDeffer.await()
            val destinationAirport = arriveDeffer.await()

            Flight(
                depart = AirportMapper.toDomain(departureAirport),
                arrive = AirportMapper.toDomain(destinationAirport),
                favorited = true,
            )
        }
}

private fun List<FavoriteFlight>.hasFavoriteFlight(
    depart: Airport,
    arrive: Airport
): Boolean {
    return this.any {
        depart.iataCode == it.departureCode && arrive.iataCode == it.destinationCode
    }
}
