package com.pratikbendre.newsapp.ui.topheadlinebysource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.repository.TopHeadlineBySourceRepository
import com.pratikbendre.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopHeadlineBySourceViewModel(private val topHeadlineBySourceRepository: TopHeadlineBySourceRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState
    fun fetchNewsBySource(source: String) {
        viewModelScope.launch {
            topHeadlineBySourceRepository.getTopHeadlineBySource(source).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsByLanguage(language: String) {
        viewModelScope.launch {
            topHeadlineBySourceRepository.getTopHeadlineByLanguage(language).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}
