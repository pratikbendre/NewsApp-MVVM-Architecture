package com.pratikbendre.newsapp.ui.offlinearticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.local.entity.Article
import com.pratikbendre.newsapp.data.repository.OfflineArticleRepository
import com.pratikbendre.newsapp.ui.base.UiState
import com.pratikbendre.newsapp.utils.AppConstants
import com.pratikbendre.newsapp.utils.DispatcherProvider
import com.pratikbendre.newsapp.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineArticleViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    private val offlineArticleRepository: OfflineArticleRepository,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetechArticles()
        } else {
            fetechArticlesFromDB()
        }
    }

    fun fetechArticles() {
        viewModelScope.launch(dispatcherProvider.io) {
            offlineArticleRepository.getArticles(AppConstants.COUNTRY_US).flowOn(Dispatchers.IO)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun fetechArticlesFromDB() {
        viewModelScope.launch(dispatcherProvider.io) {
            offlineArticleRepository.getArticlesDirectlyFromDB().flowOn(dispatcherProvider.io)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}