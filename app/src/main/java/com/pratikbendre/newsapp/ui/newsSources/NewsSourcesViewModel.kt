package com.pratikbendre.newsapp.ui.newsSources

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.NewsSources
import com.pratikbendre.newsapp.data.repository.NewsSourcesRepository
import com.pratikbendre.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NewsSourcesViewModel(private val newsSourcesRepository: NewsSourcesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<NewsSources>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<NewsSources>>> = _uiState

    init {
        viewModelScope.launch {
            newsSourcesRepository.getSources().catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}