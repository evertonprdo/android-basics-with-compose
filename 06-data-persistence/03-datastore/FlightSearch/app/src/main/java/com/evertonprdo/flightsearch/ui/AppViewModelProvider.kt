package com.evertonprdo.flightsearch.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.evertonprdo.flightsearch.FlightApplication
import com.evertonprdo.flightsearch.ui.flightsearch.FlightSearchViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val appContainer = flightApplication().container

            FlightSearchViewModel(
                appContainer.airportsRepository,
                appContainer.flightsRepository,
                appContainer.flightSearchCacheRepository,
            )
        }
    }
}

fun CreationExtras.flightApplication(): FlightApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightApplication)