package com.example.weatherappassessment.weather.domain.repository

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double,
    ) : Resource<DailyForecast>

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ) : Resource<CurrentWeather>

    suspend fun getCities(
        city: String
    ): Resource<List<Location>>

    fun getLocalDailyForecast(): Flow<DailyForecast?>
    suspend fun saveDailyForecast(weatherResponse: DailyForecast)

    fun getLocalCurrentWeather(): Flow<CurrentWeather?>
    suspend fun saveCurrentWeather(weatherResponse: CurrentWeather)

}