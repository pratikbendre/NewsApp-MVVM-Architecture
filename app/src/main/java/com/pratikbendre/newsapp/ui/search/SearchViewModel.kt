package com.pratikbendre.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.Article
import com.pratikbendre.newsapp.data.repository.SearchRepository
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.AppConstants.DEBOUNCE_TIMEOUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val query = MutableStateFlow("")


    init {
        setupflow()
    }


    fun fetchnews(searchQuery: String) {
        query.value = searchQuery
    }

    fun setupflow() {
        viewModelScope.launch {
            query.debounce(DEBOUNCE_TIMEOUT).filter {
                if (it.isNotEmpty()) {
                    return@filter true
                } else {
                    _uiState.value = UiState.Success(emptyList())
                    return@filter false
                }
            }.distinctUntilChanged().flatMapLatest {
                _uiState.value = UiState.Loading
                searchRepository.getNews(it).catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
            }.flowOn(Dispatchers.IO).collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

}