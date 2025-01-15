package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepository @Inject constructor(private val networkService: NetworkService) {
    fun getNews(value: String): Flow<List<ArticleModel>> {
        return flow {
            emit(networkService.searchNews(value))
        }.map {
            it.articleModels.filter {
                it.title != "[Removed]"
            }
        }
    }
}