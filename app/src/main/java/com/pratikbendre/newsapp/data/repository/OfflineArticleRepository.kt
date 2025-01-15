package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.api.NetworkService
import com.pratikbendre.newsapp.data.local.DatabaseService
import com.pratikbendre.newsapp.data.local.entity.Article
import com.pratikbendre.newsapp.data.model.toArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineArticleRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {
    fun getArticles(country: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .map {
                it.articleModels.map { articleModel ->
                    articleModel.toArticleEntity()
                }
            }.flatMapConcat {
                flow { emit(databaseService.deleteAllAndInsertAll(articleModels = it)) }
            }.flatMapConcat {
                databaseService.getArticles()
            }
    }

    fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return databaseService.getArticles()
    }
}