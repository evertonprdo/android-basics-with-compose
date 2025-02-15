package com.evertonprdo.cornelioprocopio_pr.ui

import com.evertonprdo.cornelioprocopio_pr.data.Location
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory
import com.evertonprdo.cornelioprocopio_pr.data.local.LocalLocationProvider

data class AppUiState(
    val locations: Map<LocationCategory, List<Location>> = emptyMap(),
    val currentCategory: LocationCategory = LocationCategory.All,
    val currentLocation: Location? = null
) {
    val currentLocations: List<Location> by lazy {
        if (currentCategory == LocationCategory.All) {
            LocalLocationProvider.allLocations
        } else {
            locations[currentCategory]!!
        }
    }
}