package com.shdwraze.chi

import android.app.Application
import com.shdwraze.chi.data.AppContainer
import com.shdwraze.chi.data.DefaultAppContainer

class ShibeApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}