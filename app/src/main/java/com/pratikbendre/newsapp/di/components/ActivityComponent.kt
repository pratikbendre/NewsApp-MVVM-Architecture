package com.pratikbendre.newsapp.di.components

import com.pratikbendre.newsapp.di.ActivityScope
import com.pratikbendre.newsapp.di.module.ActivityModule
import com.pratikbendre.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}