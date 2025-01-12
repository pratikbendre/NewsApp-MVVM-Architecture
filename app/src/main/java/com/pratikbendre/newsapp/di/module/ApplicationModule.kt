package com.pratikbendre.newsapp.di.module

import android.content.Context
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: NewsApplication) {
    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }
}