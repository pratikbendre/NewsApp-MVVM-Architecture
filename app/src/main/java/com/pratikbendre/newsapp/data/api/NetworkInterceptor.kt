package com.pratikbendre.newsapp.data.api

import com.pratikbendre.newsapp.utils.AppConstants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().header("X-Api-Key", API_KEY)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}