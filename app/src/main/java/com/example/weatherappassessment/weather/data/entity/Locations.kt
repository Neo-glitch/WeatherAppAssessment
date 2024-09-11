package com.example.weatherappassessment.weather.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Locations(
	val locations: List<Location>? = null
)

@Parcelize
data class Location(
	val country: String? = null,
	val name: String? = null,
	val lon: Double? = null,
	val state: String? = null,
	val lat: Double? = null,
): Parcelable

