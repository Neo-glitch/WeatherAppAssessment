package com.example.weatherappassessment.weather.presentation

import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val dailyForecast: DailyForecast? = null,
    val currentWeather: CurrentWeather? = null
)