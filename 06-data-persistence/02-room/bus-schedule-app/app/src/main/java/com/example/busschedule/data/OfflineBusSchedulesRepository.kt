package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusSchedulesRepository(
    private val busScheduleDao: BusScheduleDao
) : BusSchedulesRepository {

    override fun gelAllBusSchedules(): Flow<List<BusSchedule>> =
        busScheduleDao.getAllSchedules()

    override fun getBusScheduleFor(stopName: String): Flow<List<BusSchedule>> =
        busScheduleDao.getSchedulesFor(stopName)
}