package com.example.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherItem
import com.example.weatherapp.utils.FormatDate
import com.example.weatherapp.utils.FormatDateTime
import com.example.weatherapp.utils.FormatDecimals

@Composable
fun WeatherDetailRow(weatherDataItem: WeatherItem) {
    val imagerUrl = "https://openweathermap.org/img/wn/${weatherDataItem.weather[0].icon}.png"
    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(), shape = CircleShape.copy(topEnd = CornerSize(6.dp)), color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(FormatDate(weatherDataItem.dt).split(",")[0], modifier = Modifier.padding(5.dp))
            WeatherStateImage(imageUrl = imagerUrl)
            Surface(modifier = Modifier.padding(0.dp), shape = CircleShape, color = Color(0xFFFFC400)) {
                Text(text = weatherDataItem.weather[0].description, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue.copy(alpha = 0.7f), fontWeight = FontWeight.Bold)) {
                    append(FormatDecimals(weatherDataItem.temp.max) + "°")
                }
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append(FormatDecimals(weatherDataItem.temp.min) + "°")
                }
            })
        }
    }
}



@Composable
fun SunsetSunriseRow(weatherDataItem: WeatherItem) {
    Row(modifier = Modifier
        .padding(top = 15.dp, bottom = 6.dp)
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Row() {
            Icon(painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "Sunrise Icon",
                modifier =  Modifier.size(25.dp))
            Text(text = FormatDateTime(weatherDataItem.sunrise), style = MaterialTheme.typography.caption) }

        Row() {
            Icon(painter = painterResource(id = R.drawable.sunset),
                contentDescription = "Sunset Icon",
                modifier =  Modifier.size(25.dp))
            Text(text = FormatDateTime(weatherDataItem.sunset), style = MaterialTheme.typography.caption) }
    }
}

@Composable
fun HumidityWindPressureRow(weatherDataItem: WeatherItem, isImperial: Boolean) {
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "Humidity Icon",
                modifier =  Modifier.size(20.dp))
            Text(text = "${weatherDataItem.humidity} %", style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "Pressure Icon",
                modifier =  Modifier.size(20.dp))
            Text(text = "${weatherDataItem.pressure} psi", style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "Wind Icon",
                modifier =  Modifier.size(20.dp))
            Text(text = "${FormatDecimals(weatherDataItem.speed)} " + if(isImperial) "mph" else "m/s", style = MaterialTheme.typography.caption)
        }

    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl), contentDescription = "Image Icon", modifier = Modifier.size(80.dp))
}