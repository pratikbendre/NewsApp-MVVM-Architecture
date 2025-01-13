package com.pratikbendre.newsapp.di.module

import android.content.Context
import com.pratikbendre.newsapp.NewsApplication
import com.pratikbendre.newsapp.data.api.NetworkInterceptor
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.di.ApplicationContext
import com.pratikbendre.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {
    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }


    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String, gsonConverterFactory: GsonConverterFactory
    ): NetworkService {

        val networkInterceptor = NetworkInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(networkInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
            .create(NetworkService::class.java)
    }
}