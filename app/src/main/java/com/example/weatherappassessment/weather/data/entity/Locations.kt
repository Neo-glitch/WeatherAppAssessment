package com.example.weatherappassessment.weather.data.entity

data class Locations(
	val locations: List<Location>? = null
)

data class Location(
	val country: String? = null,
	val name: String? = null,
	val lon: Double? = null,
	val state: String? = null,
	val lat: Double? = null,
)

