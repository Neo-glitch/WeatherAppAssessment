package com.example.weatherappassessment.weather.data.remote.api

import com.example.weatherappassessment.BuildConfig
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import com.example.weatherappassessment.core.data.util.NetworkHelper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(NetworkHelper.WEATHER_FORECAST)
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<WeatherResponse>

    @GET(NetworkHelper.CITIES)
    suspend fun getCities(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<List<Location>>


}