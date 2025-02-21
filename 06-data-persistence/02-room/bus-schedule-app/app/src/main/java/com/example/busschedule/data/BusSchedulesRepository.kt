package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusSchedulesRepository {
    fun gelAllBusSchedules(): Flow<List<BusSchedule>>
    fun getBusScheduleFor(stopName: String): Flow<List<BusSchedule>>
}