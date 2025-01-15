package com.pratikbendre.newsapp.data.local

import com.pratikbendre.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

class AppDatabaseService constructor(private val appDatabase: AppDatabase) : DatabaseService {
    override fun getArticles(): Flow<List<Article>> {
        return appDatabase.articleDao().getAll()
    }

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        return appDatabase.articleDao().deleteAllAndInsertAll(articles)
    }
}