package com.example.weatherappassessment.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * takes a long timestamp and return the date
 */
fun formatDate(timestamp: Int): String{
    val sdf = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}


/**
 * takes a long timestamp and returns the time
 */
fun formatDateTime(timestamp: Int): String{
    val sdf = SimpleDateFormat("hh:mm:aa", Locale.getDefault())
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}
