package com.example.tools

import android.app.Application
import com.example.tools.di.components.ApplicationComponent
import com.example.tools.di.components.DaggerApplicationComponent

class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerApplicationComponent.create()
    }
}