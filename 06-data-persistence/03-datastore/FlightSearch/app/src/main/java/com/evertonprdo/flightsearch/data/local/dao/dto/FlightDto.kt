package com.evertonprdo.flightsearch.data.local.dao.dto

data class FlightDto(
    val departureId: Int,
    val departureName: String,
    val departurePassengers: Int,
    val departureIataCode: String,

    val destinationId: Int,
    val destinationName: String,
    val destinationPassengers: Int,
    val destinationIataCode: String,

    val favorited: Boolean
)
