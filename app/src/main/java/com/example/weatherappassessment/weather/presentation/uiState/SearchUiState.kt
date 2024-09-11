package com.example.weatherappassessment.weather.presentation.uiState

import com.example.weatherappassessment.core.presentation.LoadingState
import com.example.weatherappassessment.weather.data.entity.Location

data class SearchUiState(
    val loadingState: LoadingState = LoadingState.Idle,
    val errorMessage: String? = null,
    val locations: List<Location>? = null
)