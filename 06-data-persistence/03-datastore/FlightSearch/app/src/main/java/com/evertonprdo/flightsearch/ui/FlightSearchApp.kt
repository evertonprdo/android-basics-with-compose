package com.evertonprdo.flightsearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evertonprdo.flightsearch.ui.viewmodel.FlightSearchViewModel

@Composable
fun FlightSearchApp(viewModel: FlightSearchViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val list = viewModel.list.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            Text(
                "init",
                modifier = Modifier.padding(28.dp)
            )
        }

        items(list.value, key = { it.arrive.id }) {
            Text(
                "Fav: ${it.favorited} Depart: ${it.depart.iata} Arrive: ${it.arrive.iata}",
                modifier = Modifier.padding(horizontal = 28.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        item { Spacer(modifier = Modifier.height(100.dp)) }
    }
}