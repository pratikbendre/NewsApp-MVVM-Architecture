package com.pratikbendre.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pratikbendre.newsapp.data.repository.TopHeadlineRepository
import com.pratikbendre.newsapp.di.ActivityContext
import com.pratikbendre.newsapp.ui.base.ViewModelProviderFactory
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineAdapter
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun providesContext(): Context {
        return activity
    }


    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())
}