package com.example.weatherapp.repository

import android.util.Log
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api : WeatherApi) {

    suspend fun getWeather(cityQuery : String, units: String) : DataOrException<WeatherModel, Boolean, Exception> {
        val response =  try {
            api.getWeather(query =  cityQuery, units = units)
        }catch (e : Exception) {
            Log.d("INSIDE","getWeather : $e")
            return DataOrException(e = e)
        }
        Log.d("INSIDE","getWeather : $response")
        return DataOrException(data = response)
    }
}