package com.example.weatherappassessment.weather.data.repository

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import com.example.weatherappassessment.weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherResponse> =
        remoteDataSource.getWeather(latitude, longitude)


    override suspend fun getCities(city: String): Resource<List<Location>> =
        remoteDataSource.getCities(city)

    override fun getLocalWeather(): Flow<WeatherResponse?> = localDataSource.getWeatherResponse()

    override suspend fun saveWeather(weatherResponse: WeatherResponse) {
        localDataSource.saveWeatherResponse(weatherResponse)
    }
}