package com.example.weatherappassessment.weather.presentation.uiState

import com.example.weatherappassessment.weather.data.entity.Location

data class SearchUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val locations: List<Location>? = null
)