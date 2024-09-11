package com.example.weatherappassessment.weather.data.datasources.remote

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.datasources.remote.api.WeatherService
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.core.data.util.NetworkHelper
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource

class RemoteDatasourceImpl (
    private val service: WeatherService
) : RemoteDataSource{

    override suspend fun getDailyForecast(
        latitude: Double,
        longitude: Double
    ): Resource<DailyForecast> {
//        try {
//            val response = service.getWeather(latitude, longitude)
//
//            return if(response.isSuccessful) {
//                val data = response.body()
//                Resource.Success(data)
//            } else {
//                val errorMessage = NetworkHelper.getErrorMessage(response)
//                Resource.Error(errorMessage)
//            }
//        } catch (e: Exception) {
//            if (e !is CancellationException) {
//                return Resource.Error("${e.localizedMessage}")
//            }
//            e.printStackTrace()
//        }
//        return Resource.Error(NetworkHelper.NO_INTERNET_MSG)
        return NetworkHelper.handleApiCall { service.getDailyForecast(latitude, longitude) }
    }

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeather> {
        return NetworkHelper.handleApiCall { service.getCurrentWeather(latitude, longitude) }
    }

    override suspend fun getCities(city: String): Resource<List<Location>> {
        return NetworkHelper.handleApiCall { service.getCities(city) }
    }
}