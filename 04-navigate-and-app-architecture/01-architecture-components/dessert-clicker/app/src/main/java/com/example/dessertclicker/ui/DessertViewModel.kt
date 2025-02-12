package com.example.dessertclicker.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState: StateFlow<DessertUiState> = _uiState.asStateFlow()

    private var currentDessertToShown =
        determineDessertToShow(uiState.value.dessertsSold)
        set(value) {
            field = value
            currentDessertImageResId = value.imageId
        }

    var currentDessertImageResId by mutableIntStateOf(currentDessertToShown.imageId)
        private set

    fun soldDessert() {
        val dessertsSold = uiState.value.dessertsSold.inc()

        _uiState.update { currentState ->
            currentState.copy(
                dessertsSold = dessertsSold,
                totalRevenue = uiState.value.totalRevenue.plus(
                    currentDessertToShown.price
                ),
            )
        }

        currentDessertToShown = determineDessertToShow(dessertsSold)
    }

    private fun determineDessertToShow(dessertsSold: Int): Dessert {
        var dessertToShow = Datasource.dessertList.first()
        for (dessert in Datasource.dessertList) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}
