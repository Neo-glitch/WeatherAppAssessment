package com.example.weatherappassessment.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.core.presentation.LoadingState
import com.example.weatherappassessment.core.util.orZero
import com.example.weatherappassessment.weather.data.entity.WeatherData
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.model.Coordinates
import com.example.weatherappassessment.weather.domain.repository.Repository
import com.example.weatherappassessment.weather.presentation.uiState.HomeUiState
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
        repository.getCachedWeatherData()
            .onEach { weatherData ->
                weatherData?.let { data ->
                    _uiState.update {
                        it.copy(
                            loadingState = LoadingState.Loaded,
                            currentWeather = data.currentWeather, dailyForecast = data.dailyForecast
                        )
                    }
                }
            }.launchIn(viewModelScope)

        val cachedCord = repository.getLastSearchCoordinates()
        cachedCord?.let {
            updateWeatherCord(it.latitude, it.longitude)
        }
    }

    fun updateWeatherCord(latitude: Double, longitude: Double){
        this.latitude = latitude
        this.longitude = longitude

        val coordinates = Coordinates(latitude.orZero, longitude.orZero)
        cacheLastSearchedCoordinates(coordinates)

        getCurrentWeather()
    }

    private fun cacheLastSearchedCoordinates(cord: Coordinates) {
        viewModelScope.launch {
            repository.saveLastSearchCoordinates(cord)
        }
    }

    fun getCurrentWeather() {
        viewModelScope.launch {
            _uiState.update { it.copy(loadingState = LoadingState.Loading, errorMessage = null) }
            when (val result= repository.getCurrentWeather(latitude, longitude)) {
                is Resource.Error -> {
                    _uiState.update { it.copy(loadingState = LoadingState.Error, errorMessage = result.message) }
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
                _uiState.update { it.copy(loadingState = LoadingState.Error, errorMessage = result.message) }
            }
            is Resource.Success -> {
                saveWeather(data, result.data!!)
            }
        }
    }

    private fun saveWeather(currentWeather: CurrentWeather, dailyForecast: DailyForecast) {
        viewModelScope.launch {
            repository.saveWeatherData(WeatherData(currentWeather, dailyForecast))
            _uiState.update { it.copy(loadingState = LoadingState.Loaded, errorMessage = null) }
        }
    }


}