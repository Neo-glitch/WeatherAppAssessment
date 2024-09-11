package com.example.weatherappassessment.core.util

import com.example.weatherappassessment.R
import com.example.weatherappassessment.weather.data.entity.WeatherObject

val WeatherObject.getIcon: Int
    get() {
        val iconRes = when(id){
            in 200..232 -> R.drawable.ic_thunder
            in 300..321 -> R.drawable.ic_shower_rain
            in 500..531 -> R.drawable.ic_rain
            in 600..622 -> R.drawable.ic_snow
            in 701..781 -> R.drawable.ic_mist
            800 -> R.drawable.ic_clear_sky
            else -> R.drawable.ic_broken_clouds
        }
        return iconRes
    }