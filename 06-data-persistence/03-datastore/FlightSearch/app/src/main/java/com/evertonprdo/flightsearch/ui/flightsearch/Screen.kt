package com.evertonprdo.flightsearch.ui.flightsearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.ui.AppViewModelProvider
import kotlinx.coroutines.launch

@Composable
fun FlightSearchApp(
    viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val query = viewModel.userSearch.collectAsState()
    val currAirport = viewModel.currAirport.collectAsState()

    val airports = viewModel.airports.collectAsState()
    val flights = viewModel.flights.collectAsState()

    val lazyGridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    val onSelectAirport: (Airport?) -> Unit = {
        viewModel.setCurrentAirport(it)
        coroutineScope.launch { lazyGridState.animateScrollToItem(0) }
    }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {

            val searchHeight = 64.dp
            val topPadding = innerPadding.calculateTopPadding() + searchHeight

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = topPadding + 16.dp)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Flights from ${currAirport.value?.iata ?: "Favorites"}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )

                FlightList(
                    flights = flights.value,
                    onClickFavorite = viewModel::updateFlightFavorite,
                    lazyGridState = lazyGridState
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchBar(
                    search = query.value,
                    onSearchChange = viewModel::updateUserSearch,
                    airports = airports.value,
                    height = searchHeight,
                    onSelectAirport = onSelectAirport,
                    trailingIcon = {
                        SearchBarTrailingIcon(
                            onClick = { onSelectAirport(null) },
                            visible = currAirport.value != null
                        )
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .padding(top = innerPadding.calculateTopPadding())
                        .widthIn(max = 500.dp)
                )
            }
        }
    }
}

@Composable
private fun SearchBarTrailingIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    visible: Boolean = true,
) {
    if (visible)
        IconButton(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(Icons.Default.Clear, null)
        }
}