package com.evertonprdo.flightsearch

import android.app.Application
import com.evertonprdo.flightsearch.data.AppContainer
import com.evertonprdo.flightsearch.data.AppDataContainer

class FlightApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}