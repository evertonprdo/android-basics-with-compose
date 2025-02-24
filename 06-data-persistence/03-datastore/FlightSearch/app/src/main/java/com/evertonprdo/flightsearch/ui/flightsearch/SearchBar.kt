package com.evertonprdo.flightsearch.ui.flightsearch

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.evertonprdo.flightsearch.R
import com.evertonprdo.flightsearch.model.Airport
import com.evertonprdo.flightsearch.ui.components.AirportTag
import com.evertonprdo.flightsearch.ui.theme.FlightSearchTheme

@Composable
fun SearchBar(
    search: String,
    onSearchChange: (String) -> Unit,
    airports: List<Airport>,
    height: Dp,
    onSelectAirport: (Airport) -> Unit,
    trailingIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current

        val interactionSource = remember { MutableInteractionSource() }
        val isFocused = interactionSource.collectIsFocusedAsState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .padding(vertical = 4.dp)
        ) {
            OutlinedTextField(
                value = search,
                onValueChange = onSearchChange,
                singleLine = true,
                enabled = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_departure_airport),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
                    )
                },
                interactionSource = interactionSource,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = trailingIcon,
                shape = MaterialTheme.shapes.extraLarge,
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        val showAutocomplete = isFocused.value && search.isNotEmpty()
        BackHandler(showAutocomplete) { focusManager.clearFocus() }

        if (showAutocomplete)
            AutoCompleteList(
                airports = airports,
                onClickAirport = {
                    onSelectAirport(it)
                    focusManager.clearFocus()
                }
            )
    }
}

@Composable
private fun AutoCompleteList(
    airports: List<Airport>,
    onClickAirport: (Airport) -> Unit
) {
    val shape = MaterialTheme.shapes.medium

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .shadow(8.dp, shape = shape)
            .heightIn(max = 300.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = shape)
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (airport in airports) {
            Card(
                onClick = { onClickAirport(airport) },
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            ) {
                AirportTag(
                    airport = airport,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FlightSearchScreenPreview() {
    FlightSearchTheme {
        SearchBar(
            search = "",
            onSearchChange = {},
            airports = emptyList(),
            height = 64.dp,
            trailingIcon = {},
            onSelectAirport = {}
        )
    }
}
