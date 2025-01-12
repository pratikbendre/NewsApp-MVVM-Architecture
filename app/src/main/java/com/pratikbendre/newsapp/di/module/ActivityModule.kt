package com.pratikbendre.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.pratikbendre.newsapp.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun providesContext(): Context {
        return activity
    }
}