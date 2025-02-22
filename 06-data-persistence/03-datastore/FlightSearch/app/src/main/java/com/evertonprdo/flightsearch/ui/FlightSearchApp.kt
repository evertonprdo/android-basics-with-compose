package com.evertonprdo.flightsearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
    val list = viewModel.airports.collectAsState()
    val query = viewModel.userSearch.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query.value,
                onValueChange = viewModel::updateUserSearch,
                singleLine = true,
                enabled = viewModel.isInputEnabled,
                keyboardActions = KeyboardActions(
                    // onDone = viewModel.setCurrentAirport()
                )
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 28.dp)
            ) {
                items(list.value, key = { it.id }) {
                    Text(
                        "${it.iata} | ${it.name}",
                        modifier = Modifier.padding(horizontal = 28.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
    }

}