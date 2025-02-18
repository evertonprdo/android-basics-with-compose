package com.evertonprdo.amphibians.ui.viewmodel

import com.evertonprdo.amphibians.model.Amphibian

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}