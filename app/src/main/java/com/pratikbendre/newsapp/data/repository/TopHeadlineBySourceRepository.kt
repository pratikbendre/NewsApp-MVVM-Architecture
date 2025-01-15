package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.model.ArticleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineBySourceRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadlineBySource(source: String): Flow<List<ArticleModel>> {
        return flow {
            emit(networkService.getTopHeadlinesBySources(source))
        }.map {
            it.articleModels.filter {
                it.title != "[Removed]"
            }
        }
    }

    fun getTopHeadlineByLanguage(language: String): Flow<List<ArticleModel>> {
        return flow {
            emit(networkService.getTopHeadlinesByLanguage(language))
        }.map {
            it.articleModels.filter {
                it.title != "[Removed]"
            }
        }
    }

}