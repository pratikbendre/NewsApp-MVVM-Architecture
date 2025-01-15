package com.pratikbendre.newsapp.data.local

import com.pratikbendre.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    fun getArticles(): Flow<List<Article>>
    fun deleteAllAndInsertAll(articleModels: List<Article>)
}