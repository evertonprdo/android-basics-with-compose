package com.evertonprdo.amphibians

import android.app.Application
import com.evertonprdo.amphibians.data.AppContainer
import com.evertonprdo.amphibians.data.DefaultAppContainer

class AmphibiansApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}