package com.pratikbendre.newsapp.di.module

import com.pratikbendre.newsapp.ui.countries.CountriesAdapter
import com.pratikbendre.newsapp.ui.language.LanguageAdapter
import com.pratikbendre.newsapp.ui.newsSources.NewsSourcesAdapter
import com.pratikbendre.newsapp.ui.search.SearchAdapter
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineAdapter
import com.pratikbendre.newsapp.ui.topheadlinebysource.TopHeadlineBySourceAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideNewsSourcesAdapter() = NewsSourcesAdapter(ArrayList())


    @ActivityScoped
    @Provides
    fun provideTopHeadlineBySourceAdapter() = TopHeadlineBySourceAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguageAdapter() = LanguageAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideSerchAdapter() = SearchAdapter(ArrayList())
}