package com.archeros.roadmap.core

import android.app.Application
import java.lang.IllegalStateException

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: MyApplication?  = null
        fun getInstance(): MyApplication {
            if (appInstance == null)
                throw IllegalStateException("Configurar application no Android Manifest")
            return appInstance!!
        }
    }
}