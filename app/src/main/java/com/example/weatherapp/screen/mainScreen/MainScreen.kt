package com.example.weatherapp.screen.mainScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.MainActivity.Companion.MAIN_SCREEN_DETAIL_TEXT
import com.example.weatherapp.data.DataOrException
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.model.WeatherModel
import com.example.weatherapp.navigation.Screens
import com.example.weatherapp.screen.settingsScreen.SettingsScreenViewModel
import com.example.weatherapp.utils.FormatDate
import com.example.weatherapp.utils.FormatDecimals
import com.example.weatherapp.widgets.*

@Composable
fun MainScreen(navController: NavController, mainScreenViewModel: MainScreenViewModel = hiltViewModel(), settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel(), city: String?) {
    Log.d("City", "Main Screen : $city")
    val currentCity: String = if(city!!.isBlank()) "Seattle" else city
    val unitFromDb = settingsScreenViewModel.unitList.collectAsState().value
    var unit by remember { mutableStateOf("imperial") }
    var isImperial by remember { mutableStateOf(false)}

    if(!unitFromDb.isNullOrEmpty()) {
        unit = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial = unit == "imperial"
        val weatherData = produceState<DataOrException<WeatherModel, Boolean, Exception>>(
            initialValue = DataOrException(loading = true)) {
            value = mainScreenViewModel.getWeatherData(city = currentCity, units = unit)
        }.value

        if (weatherData.loading == true) {
            CircularProgressIndicator()
        } else if (weatherData.data != null) {
            MainScaffold(weatherModel = weatherData.data!!, navController = navController, isImperial = isImperial)
        }
    }


}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScaffold(weatherModel: WeatherModel, navController: NavController, isImperial: Boolean) {
    Scaffold(topBar = { WeatherAppBar(weatherModel.city.name + ", ${weatherModel.city.country}",
        navController = navController,
        onAddActionClicked = {
                navController.navigate(Screens.SearchScreen.name)
        },
        elevation = 5.dp) }) {
        MainContent(data = weatherModel, isImperial = isImperial)
    }
}

@Composable
fun MainContent(data: WeatherModel, isImperial: Boolean) {
    val weatherDataItem = data.list[0]
    val imagerUrl = "https://openweathermap.org/img/wn/${weatherDataItem.weather[0].icon}.png"
    Column(
        Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = FormatDate(weatherDataItem.dt), //wed Nov 39
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp))
        Surface(modifier = Modifier
            .padding(4.dp)
            .size(200.dp), shape = CircleShape, color = Color(0xFFFFC400)) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                WeatherStateImage(imageUrl = imagerUrl)
                Text(text = FormatDecimals(weatherDataItem.temp.day)+ "°", style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
                Text(text = weatherDataItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }
        HumidityWindPressureRow(weatherDataItem = weatherDataItem, isImperial = isImperial)
        Divider()
        SunsetSunriseRow(weatherDataItem = weatherDataItem)
        Text(text = MAIN_SCREEN_DETAIL_TEXT, style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(), color = Color(0xFFEEF1EF), shape = RoundedCornerShape(size = 14.dp)) {
            LazyColumn(modifier = Modifier.padding(2.dp), contentPadding = PaddingValues(1.dp)) {
                items(items = data.list) { item: WeatherItem ->
                    WeatherDetailRow(weatherDataItem = item)
                }
            }
        }
    }
}






