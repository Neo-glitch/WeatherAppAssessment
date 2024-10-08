package com.example.weatherappassessment.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.core.presentation.LoadingState
import com.example.weatherappassessment.weather.domain.repository.Repository
import com.example.weatherappassessment.weather.presentation.uiState.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun getCities(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading) }
            when (val result = repository.getCities(query)) {
                is Resource.Error -> {
                    _uiState.update { it.copy(loadingState = LoadingState.Error, errorMessage = result.message) }
                }
                is Resource.Success -> {
                    _uiState.update { it.copy(loadingState = LoadingState.Loaded, locations = result.data) }
                }
            }
        }
    }

    fun resetErrorState(){
        _uiState.update { it.copy(errorMessage = null) }
    }


}