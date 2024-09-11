package com.example.weatherappassessment.weather.presentation.uiState

import com.example.weatherappassessment.core.presentation.LoadingState
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast

data class HomeUiState(
    val errorMessage: String? = null,
    val loadingState: LoadingState = LoadingState.Idle,
    val dailyForecast: DailyForecast? = null,
    val currentWeather: CurrentWeather? = null
)

