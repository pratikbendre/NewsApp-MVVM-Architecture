package com.pratikbendre.newsapp

import android.app.Application
import com.pratikbendre.newsapp.di.components.ApplicationComponent
import com.pratikbendre.newsapp.di.components.DaggerApplicationComponent
import com.pratikbendre.newsapp.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }


    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}
