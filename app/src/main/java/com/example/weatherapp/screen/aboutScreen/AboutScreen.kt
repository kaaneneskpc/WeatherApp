package com.example.weatherapp.screen.aboutScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.weatherapp.MainActivity.Companion.ABOUT_SCREEN_APPBAR_TEXT
import com.example.weatherapp.R
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
        title= ABOUT_SCREEN_APPBAR_TEXT,
        icon = Icons.Default.ArrowBack,
        isMainScreen = false,
        navController = navController) {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.about_app_text), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.copy_right_text), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
                Text(text = stringResource(id = R.string.copy_right_year_text), style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
            }
        }
    }
}