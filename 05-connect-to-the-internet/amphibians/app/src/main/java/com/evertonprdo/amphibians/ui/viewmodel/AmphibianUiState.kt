package com.evertonprdo.amphibians.ui.viewmodel

import com.evertonprdo.amphibians.model.Amphibian

sealed interface AmphibianUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibianUiState
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}