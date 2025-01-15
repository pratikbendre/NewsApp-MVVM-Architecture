package com.pratikbendre.newsapp.ui.topheadlinebysource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.repository.TopHeadlineBySourceRepository
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineBySourceViewModel @Inject constructor(
    private val topHeadlineBySourceRepository: TopHeadlineBySourceRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {
    private val _uiState =
        MutableStateFlow<UiState<List<ArticleModel>>>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState<List<ArticleModel>>> = _uiState
    fun fetchNewsBySource(source: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.value = UiState.Loading
            topHeadlineBySourceRepository.getTopHeadlineBySource(source).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsByLanguage(language: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            _uiState.value = UiState.Loading
            topHeadlineBySourceRepository.getTopHeadlineByLanguage(language).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}
