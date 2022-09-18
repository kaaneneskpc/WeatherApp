package com.example.weatherapp.screen.mainScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.model.WeatherObject
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    suspend fun getWeatherData(city: String, units: String) : DataOrException<WeatherModel, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }

}