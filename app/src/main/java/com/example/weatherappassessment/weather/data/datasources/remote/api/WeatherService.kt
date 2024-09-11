package com.example.weatherappassessment.weather.data.datasources.remote.api

import com.example.weatherappassessment.BuildConfig
import com.example.weatherappassessment.weather.data.entity.Location
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.core.data.util.NetworkHelper
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET(NetworkHelper.DAILY_FORECAST)
    suspend fun getDailyForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("cnt") count: Int = 7,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<DailyForecast>

    @GET(NetworkHelper.CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<CurrentWeather>

    @GET(NetworkHelper.CITIES)
    suspend fun getCities(
        @Query("q") city: String,
        @Query("limit") limit: Int = 10,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Response<List<Location>>




}