package com.evertonprdo.flightsearch.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airport")
data class Airport(
    @PrimaryKey
    val id: Int,

    val name: String,
    val passengers: Int,

    @ColumnInfo(name = "iata_code", index = true)
    val iataCode: String,
)