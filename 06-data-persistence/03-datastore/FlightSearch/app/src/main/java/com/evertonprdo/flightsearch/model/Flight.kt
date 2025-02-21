package com.evertonprdo.flightsearch.model

data class Flight(
    val depart: Airport,
    val arrive: Airport,
    val favorited: Boolean
)