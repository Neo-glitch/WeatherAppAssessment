package com.example.weatherappassessment.weather.presentation

import com.example.weatherappassessment.weather.data.entity.WeatherResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val weatherResponse: WeatherResponse? = null
)