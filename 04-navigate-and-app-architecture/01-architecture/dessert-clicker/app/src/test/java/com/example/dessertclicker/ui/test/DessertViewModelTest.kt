package com.example.dessertclicker.ui.test

import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.ui.DessertViewModel
import org.junit.Assert
import org.junit.Test

class DessertViewModelTest {
    private val viewModel = DessertViewModel()

    companion object {
        private val FIRST_DESSERT_TO = Datasource.dessertList.first()
        private val REVENUE_AFTER_FIRST_SOLD = FIRST_DESSERT_TO.price
    }

    @Test
    fun dessertViewModel_DessertSold_UpdatesUiStateCorrectly() {
        viewModel.soldDessert()
        val currentDessertUiState = viewModel.uiState.value

        Assert.assertEquals(
            REVENUE_AFTER_FIRST_SOLD,
            currentDessertUiState.totalRevenue
        )
        Assert.assertTrue(currentDessertUiState.dessertsSold == 1)
    }

    @Test
    fun dessertViewModel_AllDessertsShown_DessertImageResIdAndUiStateUpdatedCorrectly() {
        var expectedRevenue = 0

        Datasource.dessertList.zipWithNext { currentDessert, nextDessert ->
            val unitsToBeSoldUntilTheNextDessert =
                nextDessert.startProductionAmount - currentDessert.startProductionAmount

            expectedRevenue += unitsToBeSoldUntilTheNextDessert * currentDessert.price

            repeat(unitsToBeSoldUntilTheNextDessert) {
                viewModel.soldDessert()
            }

            Assert.assertEquals(
                nextDessert.imageId,
                viewModel.currentDessertImageResId
            )

            with(viewModel.uiState.value) {
                Assert.assertEquals(
                    nextDessert.startProductionAmount,
                    dessertsSold
                )
                Assert.assertEquals(expectedRevenue, totalRevenue)
            }
        }
    }

    @Test
    fun dessertViewModel_Initialization_FirstDessertImageResIdAndUiStateLoaded() {
        val dessertUiState = viewModel.uiState.value

        Assert.assertEquals(
            viewModel.currentDessertImageResId,
            FIRST_DESSERT_TO.imageId
        )

        Assert.assertTrue(dessertUiState.dessertsSold == 0)
        Assert.assertTrue(dessertUiState.totalRevenue == 0)
    }
}