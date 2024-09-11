package com.example.weatherappassessment.weather.domain.datasource

import com.example.weatherappassessment.weather.data.entity.WeatherData
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.model.Coordinates
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveLastSearchCoordinates(cord: Coordinates)
    fun getLastSearchCoordinates(): Coordinates?

    suspend fun saveWeatherData(combinedWeatherData: WeatherData)
    fun getCachedWeatherData(): Flow<WeatherData?>
}