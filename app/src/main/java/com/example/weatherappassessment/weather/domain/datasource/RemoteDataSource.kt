package com.example.weatherappassessment.weather.domain.datasource

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast

interface RemoteDataSource {

    suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double,
    ) : Resource<DailyForecast>

    suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
    ): Resource<CurrentWeather>

    suspend fun getCities(
        city: String
    ): Resource<List<Location>>

}