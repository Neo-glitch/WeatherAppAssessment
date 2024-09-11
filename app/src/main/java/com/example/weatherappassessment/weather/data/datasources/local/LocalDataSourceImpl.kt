package com.example.weatherappassessment.weather.data.datasources.local

import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherappassessment.weather.data.entity.WeatherData
import com.example.weatherappassessment.weather.domain.datasource.LocalDataSource
import com.example.weatherappassessment.weather.domain.model.Coordinates
import com.google.gson.Gson
import com.neocalc.neocalc.core.data.datasources.local.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataSourceImpl(
    private val preferences: AppPreferences
) : LocalDataSource {

    override suspend fun saveLastSearchCoordinates(cord: Coordinates) {
        val gson = Gson()
        val responseJson = gson.toJson(cord)
        preferences.putPreferences(LAST_SEARCHED_COORDINATE_KEY, responseJson)
    }

    override fun getLastSearchCoordinates(): Coordinates? {
        val gson = Gson()
        val responseJson = preferences.getFirstPreferences(LAST_SEARCHED_COORDINATE_KEY)
        return gson.fromJson(responseJson, Coordinates::class.java)
    }

    override suspend fun saveWeatherData(combinedWeatherData: WeatherData) {
        val gson = Gson()
        val responseJson = gson.toJson(combinedWeatherData)
        preferences.putPreferences(WEATHER_DATA_KEY, responseJson)
    }

    override fun getCachedWeatherData(): Flow<WeatherData?> {
        val responseFlow = preferences.getPreferences(WEATHER_DATA_KEY).map {
            val gson = Gson()
            val responseJson = preferences.getFirstPreferences(WEATHER_DATA_KEY)
            gson.fromJson(responseJson, WeatherData::class.java)
        }
        return responseFlow
    }

    companion object {
        val LAST_SEARCHED_COORDINATE_KEY = stringPreferencesKey("last_searched_coordinate")
        val WEATHER_DATA_KEY = stringPreferencesKey("weather_data")

    }
}