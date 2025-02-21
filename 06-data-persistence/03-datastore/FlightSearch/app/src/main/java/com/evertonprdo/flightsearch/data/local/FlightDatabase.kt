package com.evertonprdo.flightsearch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evertonprdo.flightsearch.data.local.dao.AirportDao
import com.evertonprdo.flightsearch.data.local.dao.FavoriteFlightDao
import com.evertonprdo.flightsearch.data.local.dao.FlightDao
import com.evertonprdo.flightsearch.data.local.entities.Airport
import com.evertonprdo.flightsearch.data.local.entities.FavoriteFlight

@Database(
    entities = [Airport::class, FavoriteFlight::class],
    version = 1,
    exportSchema = false
)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun airportDao(): AirportDao
    abstract fun favoriteFlightDao(): FavoriteFlightDao
    abstract fun flightDao(): FlightDao

    companion object {
        @Volatile
        private var instance: FlightDatabase? = null

        fun getDatabase(context: Context): FlightDatabase {

            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context,
                        FlightDatabase::class.java,
                        "flight_search_database"
                    )
                    .createFromAsset("database/flight_search.db")
                    .build()
                    .also { instance = it }
            }
        }
    }
}