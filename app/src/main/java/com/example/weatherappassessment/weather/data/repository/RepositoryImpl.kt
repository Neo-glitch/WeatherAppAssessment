package com.example.weatherappassessment.weather.data.repository

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.entity.WeatherData
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import com.example.weatherappassessment.weather.domain.model.Coordinates
import com.example.weatherappassessment.weather.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {

    override suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double
    ): Resource<DailyForecast> =
        remoteDataSource.getDailyForecast(latitude, longitude)

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeather> = remoteDataSource.getCurrentWeather(latitude, longitude)

    override suspend fun getCities(city: String): Resource<List<Location>> =
        remoteDataSource.getCities(city)

    override suspend fun saveLastSearchCoordinates(cord: Coordinates) = localDataSource.saveLastSearchCoordinates(cord)

    override fun getLastSearchCoordinates(): Coordinates? = localDataSource.getLastSearchCoordinates()

    override suspend fun saveWeatherData(combinedWeatherData: WeatherData) {
        localDataSource.saveWeatherData(combinedWeatherData)
    }

    override fun getCachedWeatherData(): Flow<WeatherData?> {
        return localDataSource.getCachedWeatherData()
    }
}