package com.example.weatherappassessment.weather.data.remote.datasource

import com.example.weatherappassessment.core.data.util.Resource
import com.example.weatherappassessment.weather.data.remote.api.WeatherService
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import com.example.weatherappassessment.core.data.util.NetworkHelper
import com.example.weatherappassessment.weather.domain.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDatasourceImpl (
    private val service: WeatherService
) : RemoteDataSource{

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherResponse> {
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
        return NetworkHelper.handleApiCall { service.getWeather(latitude, longitude) }
    }

    override suspend fun getCities(city: String): Resource<List<Location>> {
        return NetworkHelper.handleApiCall { service.getCities(city) }
    }
}