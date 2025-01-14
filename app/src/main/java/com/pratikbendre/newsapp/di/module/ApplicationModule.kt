package com.pratikbendre.newsapp.di.module

import com.pratikbendre.newsapp.data.api.NetworkInterceptor
import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.repository.CountriesRepository
import com.pratikbendre.newsapp.data.repository.LanguageRepository
import com.pratikbendre.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

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

    @Singleton
    @Provides
    fun provideCountriesRepository() = CountriesRepository()

    @Singleton
    @Provides
    fun provideLanguageRepository() = LanguageRepository()
}