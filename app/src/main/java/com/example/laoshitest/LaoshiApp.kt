package com.example.laoshitest

import android.app.Application

class LaoshiApp: Application() {

        init {
            instance = this
        }

        companion object {
            private var instance: LaoshiApp? = null

            fun applicationContext() : LaoshiApp {
                return instance as LaoshiApp
            }
        }
        override fun onCreate() {
            super.onCreate()
        }
}