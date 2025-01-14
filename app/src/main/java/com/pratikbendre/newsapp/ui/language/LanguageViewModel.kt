package com.pratikbendre.newsapp.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.Language
import com.pratikbendre.newsapp.data.repository.LanguageRepository
import com.pratikbendre.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Success(emptyList()))
    var uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchLanguages()
    }

    fun fetchLanguages() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            languageRepository.getLanguages().catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}