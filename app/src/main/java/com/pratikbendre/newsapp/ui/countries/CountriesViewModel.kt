package com.pratikbendre.newsapp.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratikbendre.newsapp.data.model.Country
import com.pratikbendre.newsapp.data.repository.CountriesRepository
import com.pratikbendre.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val countriesRepository: CountriesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Success(emptyList()))
    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            countriesRepository.getCountris().catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}