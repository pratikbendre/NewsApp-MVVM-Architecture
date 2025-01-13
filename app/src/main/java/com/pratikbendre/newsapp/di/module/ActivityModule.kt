package com.pratikbendre.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.pratikbendre.newsapp.data.repository.CountriesRepository
import com.pratikbendre.newsapp.data.repository.NewsSourcesRepository
import com.pratikbendre.newsapp.data.repository.TopHeadlineBySourceRepository
import com.pratikbendre.newsapp.data.repository.TopHeadlineRepository
import com.pratikbendre.newsapp.di.ActivityContext
import com.pratikbendre.newsapp.ui.base.ViewModelProviderFactory
import com.pratikbendre.newsapp.ui.countries.CountriesAdapter
import com.pratikbendre.newsapp.ui.countries.CountriesViewModel
import com.pratikbendre.newsapp.ui.newsSources.NewsSourcesAdapter
import com.pratikbendre.newsapp.ui.newsSources.NewsSourcesViewModel
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineAdapter
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineViewModel
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceAdapter
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceViewModel
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

    @Provides
    fun provideNewsSourceListViewModel(newsSourcesRepository: NewsSourcesRepository): NewsSourcesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(NewsSourcesViewModel::class) {
                NewsSourcesViewModel(newsSourcesRepository)
            })[NewsSourcesViewModel::class.java]
    }

    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())
    @Provides
    fun provideTopHeadlineBySourceViewModel(topHeadlineBySourceRepository: TopHeadlineBySourceRepository): TopHeadlineBySourceViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineBySourceViewModel::class) {
                TopHeadlineBySourceViewModel(topHeadlineBySourceRepository)
            })[TopHeadlineBySourceViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineBySourceAdapter() = TopHeadlineBySourceAdapter(ArrayList())


    @Provides
    fun provideCountriesViewModel(countriesRepository: CountriesRepository): CountriesViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(CountriesViewModel::class) {
                CountriesViewModel(countriesRepository)
            })[CountriesViewModel::class.java]
    }
    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())
    @Provides
    fun provideCountriesRepository() = CountriesRepository()
}