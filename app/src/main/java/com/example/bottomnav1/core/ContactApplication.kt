package com.example.bottomnav1.core

import android.app.Application

class ContactApplication : Application()  {
    companion object {
        lateinit var container: AppContainer
    }

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer()
    }
}
