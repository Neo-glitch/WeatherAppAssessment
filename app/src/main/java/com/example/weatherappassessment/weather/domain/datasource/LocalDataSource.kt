package com.example.weatherappassessment.weather.domain.datasource

import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun saveDailyForecast(weatherResponse: DailyForecast)
    fun getDailyForecast(): Flow<DailyForecast?>

    suspend fun saveCurrentWeather(weather: CurrentWeather)
    fun getCurrentWeather(): Flow<CurrentWeather?>
}