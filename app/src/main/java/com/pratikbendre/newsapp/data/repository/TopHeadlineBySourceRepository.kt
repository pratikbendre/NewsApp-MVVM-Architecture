package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineBySourceRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadlineBySource(source: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesBySources(source))
        }.map {
            it.articles
        }
    }
}