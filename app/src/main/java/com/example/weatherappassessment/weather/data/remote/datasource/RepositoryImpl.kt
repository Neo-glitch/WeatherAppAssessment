package com.example.weatherappassessment.weather.data.remote.datasource

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.remote.entity.Location
import com.example.weatherappassessment.weather.data.remote.entity.WeatherResponse
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import com.example.weatherappassessment.weather.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl (
    private val dataSource: RemoteDataSource
) : Repository {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherResponse> =
        dataSource.getWeather(latitude, longitude)


    override suspend fun getCities(city: String): Resource<List<Location>> =
        dataSource.getCities(city)
}