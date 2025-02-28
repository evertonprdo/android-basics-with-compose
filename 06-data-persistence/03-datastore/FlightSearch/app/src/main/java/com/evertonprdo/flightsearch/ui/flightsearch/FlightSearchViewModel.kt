package com.evertonprdo.flightsearch.ui.flightsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.data.repositories.FlightSearchCacheRepository
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.model.Flight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val airportsRepository: AirportsRepository,
    private val flightsRepository: FlightsRepository,
    private val flightSearchCacheRepository: FlightSearchCacheRepository
) : ViewModel() {
    private val _userSearch = MutableStateFlow("")
    val userSearch: StateFlow<String> = _userSearch

    init {
        viewModelScope.launch {
            _userSearch.value =
                flightSearchCacheRepository.airportIataCode.first() ?: ""
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val currAirport: StateFlow<Airport?> =
        flightSearchCacheRepository.airportIataCode.flatMapLatest {
            if (it == null)
                flowOf(null)
            else
                airportsRepository.getAirport(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = null
        )

    @OptIn(ExperimentalCoroutinesApi::class)
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

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val airports: StateFlow<List<Airport>> =
        userSearch
            .debounce(300)
            .distinctUntilChanged()
            .flatMapLatest { airport -> airportsRepository.fetchAirports(airport) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = emptyList()
            )

    fun updateUserSearch(query: String) {
        _userSearch.value = query
    }

    fun updateFlightFavorite(flight: Flight) {
        viewModelScope.launch {
            flightsRepository.updateFavoriteFlight(flight)
        }
    }

    fun setCurrentAirport(airport: Airport?) {
        viewModelScope.launch {
            flightSearchCacheRepository.cacheIataCode(airport?.iata)
        }
        _userSearch.value = airport?.iata ?: ""
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
