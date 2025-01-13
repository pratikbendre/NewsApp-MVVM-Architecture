package com.pratikbendre.newsapp.data.repository

import com.pratikbendre.newsapp.data.model.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class LanguageRepository {

    fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(getLanguagesList())
        }.map {
            it
        }
    }

    private fun getLanguagesList(): List<Language> {
        return listOf(
            Language("Arabic", "ar"),
            Language("German", "de"),
            Language("English", "en"),
            Language("Spanish", "es"),
            Language("French", "fr"),
            Language("Hindi", "he"),
            Language("Italian", "it"),
            Language("Dutch", "nl"),
            Language("Norwegian", "no"),
            Language("Portuguese", "pt"),
            Language("Russian", "ru"),
            Language("Swedish", "sv"),
            Language("Urdu", "ur"),
            Language("Chinese", "zh"),
        )
    }
}