package com.evertonprdo.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.amphibians.R
import com.evertonprdo.amphibians.ui.screens.HomeScreen
import com.evertonprdo.amphibians.ui.viewmodel.AmphibiansViewModel

@Composable
fun AmphibiansApp(
    amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
) {
    Scaffold(
        topBar = { AmphibiansTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreen(
            amphibiansUiState = amphibiansViewModel.amphibianUiState,
            retryAction = amphibiansViewModel::getAmphibians,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) }
    )
}