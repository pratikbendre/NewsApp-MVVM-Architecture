package com.pratikbendre.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pratikbendre.newsapp.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("Select * FROM article")
    fun getAll(): Flow<List<Article>>

    @Insert
    fun insertAll(articleModels: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAllAndInsertAll(articles: List<Article>) {
        deleteAll()
        return insertAll(articles)
    }
}