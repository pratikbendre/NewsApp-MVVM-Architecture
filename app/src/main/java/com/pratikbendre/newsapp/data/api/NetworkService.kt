package com.pratikbendre.newsapp.data.api

import com.pratikbendre.newsapp.data.model.SourcesResponse
import com.pratikbendre.newsapp.data.model.TopHeadlinesResponse
import com.pratikbendre.newsapp.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @Headers("X-Api-Key: ${API_KEY}")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: ${API_KEY}")
    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse

    @Headers("X-Api-Key: ${API_KEY}")
    @GET("top-headlines")
    suspend fun getTopHeadlinesBySources(@Query("sources") sources: String): TopHeadlinesResponse
}