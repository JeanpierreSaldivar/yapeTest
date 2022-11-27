package com.saldivar.yapetest.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@HiltAndroidApp
class YapeTestApp : Application() {


    lateinit var applicationModule: AppModule

    override fun onCreate() {
        super.onCreate()

        applicationModule = AppModule
    }

}