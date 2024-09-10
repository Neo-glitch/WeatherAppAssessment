package com.example.weatherappassessment.weather.domain.model

import com.example.weatherappassessment.R

data class WeatherInfo(
    val title: String,
    val value: Int,
    val type: WeatherInfoType
)

enum class WeatherInfoType(iconDrawable: Int){
    Temp(R.drawable.ic_temp),
    Humidity(R.drawable.ic_humidity),
    WindSpeed(R.drawable.ic_wind)
}