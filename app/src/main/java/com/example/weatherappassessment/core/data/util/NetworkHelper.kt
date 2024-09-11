package com.example.weatherappassessment.core.data.util

import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

object NetworkHelper {
    const val DAILY_FORECAST = "data/2.5/forecast/daily"
    const val CITIES = "geo/1.0/direct"
    const val CURRENT_WEATHER = "data/2.5/weather"

    const val NETWORK_ERROR_MSG = "Network error, please try again later"
    const val NO_INTERNET_MSG = "Please check your internet connection"
    const val SERVER_ERROR_MSG = "Unknown server error, try again later"
    const val CLIENT_ERROR_MSG = "Oops! Something went wrong!"

    suspend fun <T> handleApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                Resource.Success(data)
            } else {
                val errorMessage = getErrorMessage(response)
                Resource.Error(errorMessage)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Resource.Error(e.localizedMessage ?: CLIENT_ERROR_MSG)
        }
    }

    private inline fun <T> getErrorMessage(response: Response<T>): String {
        val errorBodyString = response.errorBody()?.string()!!
        var errorMsg: String
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(errorBodyString)
            errorMsg = jsonObject.getString("message")
        } catch (e: JSONException) {
            e.printStackTrace()
            errorMsg = CLIENT_ERROR_MSG
        }
        return errorMsg
    }
}