package com.fabrizio.skaterapp

import android.app.Application

class SkaterApplication: Application() {

    companion object {
        lateinit var application: SkaterApplication
    }

    override fun onCreate() {
        super.onCreate()

        application= this

        SharedPrefUtil.init(this)


    }
}