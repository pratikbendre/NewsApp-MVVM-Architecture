package com.pratikbendre.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.repository.TopHeadlineRepository
import com.pratikbendre.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNews(country: String) {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(country)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }


    fun fetchNewsByLanguage(languages: List<String>) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val headlineFlows = languages.map { language ->
                topHeadlineRepository.getTopHeadlinesByLanguage(language)
            }
            headlineFlows.reduce { flow1, flow2 ->
                flow1.zip(flow2) { headlines1, headlines2 ->
                    val allArticlesFromApi = mutableListOf<Article>()
                    allArticlesFromApi.addAll(headlines1)
                    allArticlesFromApi.addAll(headlines2)
                    return@zip allArticlesFromApi
                }
            }.map {
                it.shuffled()
            }.catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

}