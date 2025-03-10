package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BusSchedule::class],
    version = 1,
    exportSchema = false
)
abstract class BusScheduleDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var instance: BusScheduleDatabase? = null

        fun getDatabase(context: Context): BusScheduleDatabase {

            return instance ?: synchronized(this) {
                Room
                    .databaseBuilder(
                        context,
                        BusScheduleDatabase::class.java,
                        "bus_schedule_database"
                    )
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                    .also { instance = it }
            }
        }
    }
}