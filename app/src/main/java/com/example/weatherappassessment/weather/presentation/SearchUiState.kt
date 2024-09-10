package com.example.weatherappassessment.weather.presentation

import com.example.weatherappassessment.weather.data.remote.entity.Location

data class SearchUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val locations: List<Location>? = null
)