package com.pratikbendre.newsapp.ui.topheadlinepagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.pratikbendre.newsapp.data.model.ArticleModel
import com.pratikbendre.newsapp.data.repository.TopHeadlinePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlinePaginationViewModel @Inject constructor(private val topHeadlinePagingRepository: TopHeadlinePagingRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<PagingData<ArticleModel>>(value = PagingData.empty())
    val uiState: StateFlow<PagingData<ArticleModel>> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            topHeadlinePagingRepository.geTopHeadlines()
                .collect {
                    _uiState.value = it
                }
        }
    }
}