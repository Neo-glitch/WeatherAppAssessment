package com.example.weatherappassessment.weather.domain.datasource

import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveWeatherResponse(weatherResponse: WeatherResponse)
    fun getWeatherResponse(): Flow<WeatherResponse?>
}