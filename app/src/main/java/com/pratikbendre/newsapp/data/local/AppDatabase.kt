package com.pratikbendre.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pratikbendre.newsapp.data.local.dao.ArticleDao
import com.pratikbendre.newsapp.data.local.entity.Article


@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}