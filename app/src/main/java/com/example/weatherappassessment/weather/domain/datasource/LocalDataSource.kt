package com.example.weatherappassessment.weather.domain.datasource

import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.model.Coordinates
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveDailyForecast(weatherResponse: DailyForecast)
    fun getDailyForecast(): Flow<DailyForecast?>

    suspend fun saveCurrentWeather(weather: CurrentWeather)
    fun getCurrentWeather(): Flow<CurrentWeather?>

    suspend fun saveLastSearchCoordinates(cord: Coordinates)
    fun getLastSearchCoordinates(): Coordinates?
}