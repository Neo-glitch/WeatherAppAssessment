package com.example.weatherappassessment.weather.data.datasources.local

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherappassessment.weather.data.entity.WeatherResponse
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.google.gson.Gson
import com.neocalc.neocalc.core.data.datasources.local.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val preferences: AppPreferences
) : LocalDataSource {
    override suspend fun saveWeatherResponse(weatherResponse: WeatherResponse) {
        val gson = Gson()
        val responseJson = gson.toJson(weatherResponse)
        preferences.putPreferences(SAVED_WEATHER_RESPONSE_KEY, responseJson)
    }

    override fun getWeatherResponse(): Flow<WeatherResponse?> {
        val responseFlow = preferences.getPreferences(SAVED_WEATHER_RESPONSE_KEY).map {
            val gson = Gson()
            val responseJson = preferences.getFirstPreferences(SAVED_WEATHER_RESPONSE_KEY)
            gson.fromJson(responseJson, WeatherResponse::class.java)
        }
        return responseFlow
    }

    companion object {
        val SAVED_WEATHER_RESPONSE_KEY = stringPreferencesKey("saved_weather_response_key")
    }
}