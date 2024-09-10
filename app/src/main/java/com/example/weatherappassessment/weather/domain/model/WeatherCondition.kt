package com.example.weatherappassessment.weather.domain.model

import com.example.weatherappassessment.R

enum class WeatherCondition(val description: String, val icon: Int) {
    CLEAR_SKY("clear sky", R.drawable.ic_clear_sky),
    FEW_CLOUDS("few clouds", R.drawable.ic_few_clouds),
    SCATTERED_CLOUDS("scattered clouds", R.drawable.ic_scattered_clouds),
    BROKEN_CLOUDS("broken clouds", R.drawable.ic_broken_clouds),
    SHOWER_RAIN("shower rain", R.drawable.ic_shower_rain),
    RAIN("rain", R.drawable.rain),
    THUNDERSTORM("thunderstorm", R.drawable.ic_thunder),
    SNOW("snow", R.drawable.ic_snow),
    MIST("mist", R.drawable.ic_mist);

    companion object {

        fun getWeatherIcon(desc: String): Int{
            return when(desc) {
                CLEAR_SKY.description -> CLEAR_SKY.icon
                FEW_CLOUDS.description -> FEW_CLOUDS.icon
                SCATTERED_CLOUDS.description -> SCATTERED_CLOUDS.icon
                BROKEN_CLOUDS.description -> BROKEN_CLOUDS.icon
                SHOWER_RAIN.description -> SHOWER_RAIN.icon
                RAIN.description -> RAIN.icon
                THUNDERSTORM.description -> THUNDERSTORM.icon
                SNOW.description -> SNOW.icon
                else -> MIST.icon
            }
        }
    }

}