package com.example.weatherappassessment.weather.domain.repository

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.WeatherResponse

interface Repository {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
    ) : Resource<WeatherResponse>

    suspend fun getCities(
        city: String
    ): Resource<List<Location>>
}