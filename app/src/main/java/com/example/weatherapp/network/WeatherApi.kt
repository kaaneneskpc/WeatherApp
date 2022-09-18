package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface WeatherApi {

    @GET( value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY
        ): WeatherModel
}