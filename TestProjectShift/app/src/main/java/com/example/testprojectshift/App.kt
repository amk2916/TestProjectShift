package com.example.testprojectshift

import android.app.Application
import com.example.testprojectshift.di.DaggerAppComponent
import com.example.testprojectshift.di.DataBaseModule

class App:Application() {
    val component by lazy {
        DaggerAppComponent
            .builder()
            .dataBaseModule(DataBaseModule(applicationContext))
            .build()
    }
}