package com.evertonprdo.cornelioprocopio_pr.ui

import androidx.lifecycle.ViewModel
import com.evertonprdo.cornelioprocopio_pr.data.Location
import com.evertonprdo.cornelioprocopio_pr.data.LocationCategory
import com.evertonprdo.cornelioprocopio_pr.data.local.LocalLocationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState

    init {
        initializeUIState()
    }

    private fun initializeUIState() {
        val locations: Map<LocationCategory, List<Location>> =
            LocalLocationProvider.allLocations.groupBy { it.category }

        _uiState.value = AppUiState(locations = locations)
    }

    fun updateCurrentLocation(location: Location?) {
        _uiState.update {
            it.copy(
                currentLocation = location
            )
        }
    }

    fun updateCurrentCategory(category: LocationCategory) {
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }
}