package com.example.weatherappassessment.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
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

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        repository
            .getLocalCurrentWeather()
            .onEach { currentWeather ->
                _uiState.update { it.copy(currentWeather = currentWeather)}
            }.launchIn(viewModelScope)

        repository
            .getLocalDailyForecast()
            .onEach { dailyForecast ->
               _uiState.update { it.copy(dailyForecast = dailyForecast) }
            }.launchIn(viewModelScope)
    }

    fun updateWeatherCord(latitude: Double, longitude: Double){
        this.latitude = latitude
        this.longitude = longitude
        getCurrentWeather()
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            when (val result= repository.getCurrentWeather(latitude, longitude)) {
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
                }
                is Resource.Success -> {
                    getDailyForecast(result.data!!)
                }
            }
        }
    }

    private suspend fun getDailyForecast(data: CurrentWeather) {
        when(val result = repository.getDailyForecast(latitude, longitude)) {
            is Resource.Error -> {
                _uiState.update { it.copy(isLoading = false, errorMessage = result.message) }
            }
            is Resource.Success -> {
                _uiState.update { it.copy(isLoading = false, errorMessage = null) }
                saveWeather(data, result.data!!)
            }
        }
    }

    fun saveWeather(currentWeather: CurrentWeather, dailyForecast: DailyForecast) {
        viewModelScope.launch {
            repository.saveCurrentWeather(currentWeather)
            repository.saveDailyForecast(dailyForecast)
        }
    }


}