package com.example.weatherappassessment.weather.data.entity

data class DailyForecast(
	val city: City? = null,
	val cnt: Int? = null,
	val cod: String? = null,
	val message: Double? = null,
	val list: List<WeatherItem>? = null
)

data class WeatherObject(
	val icon: String? = null,
	val description: String? = null,
	val main: String? = null,
	val id: Int? = null
)

data class Temp(
	val min: Double? = null,
	val max: Double? = null,
	val eve: Double? = null,
	val night: Double? = null,
	val day: Double? = null,
	val morn: Double? = null
)

data class Coord(
	val lon: Double? = null,
	val lat: Double? = null
)

data class WeatherItem(
	val sunrise: Int? = null,
	val temp: Temp? = null,
	val deg: Int? = null,
	val pressure: Int? = null,
	val clouds: Int? = null,
	val feelsLike: FeelsLike? = null,
	val speed: Double? = null,
	val dt: Int? = null,
	val sunset: Int? = null,
	val weather: List<WeatherObject>? = null,
	val humidity: Int? = null,
	val gust: Double? = null
)

data class City(
	val country: String? = null,
	val coord: Coord? = null,
	val timezone: Int? = null,
	val name: String? = null,
	val id: Int? = null,
	val population: Int? = null
)

data class FeelsLike(
	val eve: Double? = null,
	val night: Double? = null,
	val day: Double? = null,
	val morn: Double? = null
)

