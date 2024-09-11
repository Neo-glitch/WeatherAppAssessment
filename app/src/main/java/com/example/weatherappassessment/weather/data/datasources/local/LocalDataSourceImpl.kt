package com.example.weatherappassessment.weather.data.datasources.local

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherappassessment.weather.data.entity.CurrentWeather
import com.example.weatherappassessment.weather.data.entity.DailyForecast
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.google.gson.Gson
import com.neocalc.neocalc.core.data.datasources.local.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val preferences: AppPreferences
) : LocalDataSource {
    override suspend fun saveDailyForecast(weatherResponse: DailyForecast) {
        val gson = Gson()
        val responseJson = gson.toJson(weatherResponse)
        preferences.putPreferences(DAILY_FORECAST_KEY, responseJson)
    }

    override fun getDailyForecast(): Flow<DailyForecast?> {
        val responseFlow = preferences.getPreferences(DAILY_FORECAST_KEY).map {
            val gson = Gson()
            val responseJson = preferences.getFirstPreferences(DAILY_FORECAST_KEY)
            gson.fromJson(responseJson, DailyForecast::class.java)
        }
        return responseFlow
    }

    override suspend fun saveCurrentWeather(weather: CurrentWeather) {
        val gson = Gson()
        val responseJson = gson.toJson(weather)
        preferences.putPreferences(CURRENT_WEATHER_KEY, responseJson)
    }

    override fun getCurrentWeather(): Flow<CurrentWeather?> {
        val responseFlow = preferences.getPreferences(CURRENT_WEATHER_KEY).map {
            val gson = Gson()
            val responseJson = preferences.getFirstPreferences(CURRENT_WEATHER_KEY)
            gson.fromJson(responseJson, CurrentWeather::class.java)
        }
        return responseFlow
    }

    companion object {
        val DAILY_FORECAST_KEY = stringPreferencesKey("daily_forecast")
        val CURRENT_WEATHER_KEY = stringPreferencesKey("current_weather")

    }
}