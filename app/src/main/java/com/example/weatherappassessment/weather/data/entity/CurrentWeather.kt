package com.example.weatherappassessment.weather.data.entity

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
	val visibility: Int? = null,
	val timezone: Int? = null,
	val main: Main? = null,
	val clouds: Clouds? = null,
	val sys: Sys? = null,
	val dt: Int? = null,
	val coord: Coord? = null,
	val weather: List<WeatherObject>? = null,
	val name: String? = null,
	val cod: Int? = null,
	val id: Int? = null,
	val base: String? = null,
	val wind: Wind? = null
)

data class Main(
	val temp: Double? = null,
	@SerializedName("temp_min")
	val tempMin: Double? = null,
	@SerializedName("grnd_level")
	val grndLevel: Int? = null,
	val humidity: Int? = null,
	val pressure: Int? = null,
	@SerializedName("sea_level")
	val seaLevel: Int? = null,
	@SerializedName("feels_like")
	val feelsLike: Double? = null,
	@SerializedName("temp_max")
	val tempMax: Double? = null
)

data class Clouds(
	val all: Int? = null
)

data class Sys(
	val country: String? = null,
	val sunrise: Int? = null,
	val sunset: Int? = null
)

data class Wind(
	val deg: Int? = null,
	val speed: Double? = null,
	val gust: Double? = null
)

