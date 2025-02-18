package com.evertonprdo.amphibians

import com.evertonprdo.amphibians.fake.FakeDataSource
import com.evertonprdo.amphibians.fake.FakeNetworkAmphibiansRepository
import com.evertonprdo.amphibians.rules.TestDispatcherRule
import com.evertonprdo.amphibians.ui.viewmodel.AmphibiansUiState
import com.evertonprdo.amphibians.ui.viewmodel.AmphibiansViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class AmphibiansViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun amphibiansViewModelTest_getAmphibians() = runTest {
        val viewModel = AmphibiansViewModel(
            amphibiansRepository = FakeNetworkAmphibiansRepository()
        )

        Assert.assertEquals(
            AmphibiansUiState.Success(FakeDataSource.amphibianList),
            viewModel.amphibianUiState
        )
    }
}