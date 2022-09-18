package com.example.weatherapp.utils

import java.text.SimpleDateFormat

fun FormatDate(timestamp: Int): String {
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun FormatDateTime(timestamp: Int): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = java.util.Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun FormatDecimals(item: Double): String {
    return " %.0f".format(item)
}