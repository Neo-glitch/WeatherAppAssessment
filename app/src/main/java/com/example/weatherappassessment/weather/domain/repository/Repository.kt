package com.example.weatherappassessment.weather.domain.repository

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.WeatherData
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.model.Coordinates
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

    suspend fun saveLastSearchCoordinates(cord: Coordinates)
    fun getLastSearchCoordinates(): Coordinates?

    suspend fun saveWeatherData(combinedWeatherData: WeatherData)
    fun getCachedWeatherData(): Flow<WeatherData?>

}