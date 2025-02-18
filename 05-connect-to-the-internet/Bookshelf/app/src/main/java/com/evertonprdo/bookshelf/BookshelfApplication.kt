package com.evertonprdo.bookshelf

import android.app.Application
import com.evertonprdo.bookshelf.data.AppContainer
import com.evertonprdo.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}