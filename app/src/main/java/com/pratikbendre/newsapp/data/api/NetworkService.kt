package com.pratikbendre.newsapp.data.api

import com.pratikbendre.newsapp.data.model.SourcesResponse
import com.pratikbendre.newsapp.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {


    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse


    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse


    @GET("top-headlines")
    suspend fun getTopHeadlinesBySources(@Query("sources") sources: String): TopHeadlinesResponse


    @GET("top-headlines")
    suspend fun getTopHeadlinesByLanguage(@Query("language") sources: String): TopHeadlinesResponse


    @GET("everything")
    suspend fun searchNews(@Query("q") data: String): TopHeadlinesResponse
}