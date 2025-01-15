package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadlines(country: String): Flow<List<ArticleModel>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articleModels.filter {
                it.title != "[Removed]"
            }
        }
    }


    fun getTopHeadlinesByLanguage(language: String): Flow<List<ArticleModel>> {
        return flow {
            emit(networkService.getTopHeadlinesByLanguage(language))
        }.map {
            it.articleModels.filter {
                it.title != "[Removed]"
            }
        }
    }
}