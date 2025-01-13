package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.NewsSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {
    fun getSources(): Flow<List<NewsSources>> {
        return flow {
            emit(networkService.getSources())
        }.map {
            it.source
        }
    }
}