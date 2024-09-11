package com.example.weatherappassessment.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import com.example.weatherappassessment.weather.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel(){

//    private var latitude: Double?
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        repository.getLocalWeather()
            .onEach { response ->
               _uiState.update { it.copy(localWeatherResponse = response) }
            }.launchIn(viewModelScope)
    }

    fun getWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result= repository.getWeather(latitude, longitude)) {
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, remoteWeatherResponse = result.data) }
                }
            }
        }
    }

    fun saveWeather(response: WeatherResponse) {
        viewModelScope.launch {
            repository.saveWeather(response)
        }
    }


}