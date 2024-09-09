package com.example.weatherappassessment.core.data.util

sealed class Resource {
    data class Success<out T>(val data: T): Resource()
    data class Error(val message: String) : Resource()
}