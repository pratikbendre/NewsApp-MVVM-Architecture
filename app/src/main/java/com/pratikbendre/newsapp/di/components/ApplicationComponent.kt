package com.pratikbendre.newsapp.di.components

import android.content.Context
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.repository.TopHeadlineRepository
import com.pratikbendre.newsapp.di.ApplicationContext
import com.pratikbendre.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository
}