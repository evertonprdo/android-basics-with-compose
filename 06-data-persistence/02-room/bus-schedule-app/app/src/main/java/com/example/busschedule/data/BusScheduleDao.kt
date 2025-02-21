package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM Schedule WHERE stop_name = :stopName")
    fun getSchedulesFor(stopName: String): Flow<List<BusSchedule>>

    @Query("SELECT * FROM Schedule")
    fun getAllSchedules(): Flow<List<BusSchedule>>
}