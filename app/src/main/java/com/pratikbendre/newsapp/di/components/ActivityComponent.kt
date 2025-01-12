package com.pratikbendre.newsapp.di.components

import com.pratikbendre.newsapp.di.ActivityScope
import com.pratikbendre.newsapp.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
}