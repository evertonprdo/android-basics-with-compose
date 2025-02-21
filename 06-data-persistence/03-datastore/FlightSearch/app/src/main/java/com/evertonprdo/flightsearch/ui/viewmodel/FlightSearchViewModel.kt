package com.evertonprdo.flightsearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evertonprdo.flightsearch.data.repositories.AirportsRepository
import com.evertonprdo.flightsearch.data.repositories.FlightsRepository
import com.evertonprdo.flightsearch.model.Airport
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FlightSearchViewModel(
    private val airportsRepository: AirportsRepository,
    private val flightsRepository: FlightsRepository
) : ViewModel() {

    var list: StateFlow<List<Airport>> =
        airportsRepository
            .fetchAirports("")
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = listOf()
            )
}