package com.evertonprdo.flightsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.data.repositories.FlightSearchCacheRepository
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val airportsRepository: AirportsRepository,
    private val flightsRepository: FlightsRepository,
    private val flightSearchCacheRepository: FlightSearchCacheRepository
) : ViewModel() {

    val cachedIataCode: StateFlow<String?> =
        flightSearchCacheRepository.getCachedIataCode.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = null
        )

    val currAirport: StateFlow<Airport?> =
        flightSearchCacheRepository.getCachedIataCode.flatMapLatest {
            if (it == null)
                flowOf(null)
            else
                airportsRepository.getAirport(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = null
        )

    val flights: StateFlow<List<Flight>> =
        currAirport.flatMapLatest {
            if (it == null)
                flightsRepository.getFavoriteFlights()
            else
                flightsRepository.fetchFlights(it.iata)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = emptyList()
        )

    fun setCurrentAirport(iata: String?) {
        viewModelScope.launch {
            flightSearchCacheRepository.cacheIataCode(iata)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
