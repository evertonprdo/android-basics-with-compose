package com.evertonprdo.flightsearch.ui.flightsearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.evertonprdo.flightsearch.model.Flight
import com.evertonprdo.flightsearch.ui.components.AirportTag

@Composable
fun FlightList(
    flights: List<Flight>,
    onClickFavorite: (Flight) -> Unit,
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState = rememberLazyGridState(),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(320.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        state = lazyGridState,
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(
            flights,
            key = { "${it.arrive.id}_${it.depart.id}" }
        ) {
            CardFlight(
                flight = it,
                modifier = Modifier.wrapContentSize(),
                onClickFavorite = onClickFavorite
            )
        }
    }
}

@Composable
fun CardFlight(
    flight: Flight,
    onClickFavorite: (Flight) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "DEPART",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    AirportTag(flight.depart)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "ARRIVE",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                    AirportTag(flight.arrive)
                }
            }

            IconButton(onClick = { onClickFavorite(flight) }) {
                val imgVector =
                    if (flight.favorited) Icons.Default.Favorite
                    else Icons.Default.FavoriteBorder

                Icon(
                    imageVector = imgVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}