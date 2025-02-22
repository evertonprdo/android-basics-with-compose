package com.evertonprdo.flightsearch.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.evertonprdo.flightsearch.data.local.FlightDatabase

object RoomInMemoryDatabase {
    fun getDatabase(): FlightDatabase {
        val flightDatabase = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                FlightDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()

        populateDatabase(flightDatabase)
        return flightDatabase
    }

    private fun populateDatabase(flightDatabase: FlightDatabase) {
        flightDatabase.openHelper.writableDatabase.execSQL(
            """
            INSERT INTO airport (name, iata_code, passengers)
            VALUES
                ('test-01', 'TS1', 1000),
                ('test-02', 'TS2', 2000),
                ('test-03', 'TS3', 3000);
        """
        )
    }
}