package com.example.bs217.androidhub

import android.app.Application
import com.orm.SugarContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SugarContext.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        SugarContext.terminate()
    }
}