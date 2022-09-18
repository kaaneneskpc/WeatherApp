package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.navigation.WeatherNavigation
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WeatherApp()
                }
            }
        }
    }
    companion object {
        val MAIN_SCREEN_DETAIL_TEXT = "This Week"
        val SEARCH_SCREEN_APPBAR_TEXT = "Search"
        val SEARCH_SCREEN_SEARCH_CITY_TEXT = "City"
        val ABOUT_SCREEN_APPBAR_TEXT = "About"
        val FAVORITES_SCREEN_APPBAR_TEXT = "Favorites Cities"
        val SETTINGS_SCREEN_APPBAR_TEXT = "Settings"
        val IMPERIAL = "Imperial (F)"
        val METRIC = "Metric (C)"
        val FAHRENHEIT = "Fahrenheit °F"
        val CELSIUS = "Celsius °C"
    }
}

@Composable
fun WeatherApp() {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
           WeatherNavigation()
    }
}