package com.example.weatherapp.screen.settingsScreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.MainActivity
import com.example.weatherapp.MainActivity.Companion.CELSIUS
import com.example.weatherapp.MainActivity.Companion.FAHRENHEIT
import com.example.weatherapp.MainActivity.Companion.IMPERIAL
import com.example.weatherapp.MainActivity.Companion.METRIC
import com.example.weatherapp.R
import com.example.weatherapp.model.Unit
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController, settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()) {
    val shape = RoundedCornerShape(12.dp)
    var unitToggleState by remember { mutableStateOf(false) }
    val measurementUnits = listOf(IMPERIAL, METRIC)
    val choiceFromDb = settingsScreenViewModel.unitList.collectAsState().value
    val defaultChoice = if (choiceFromDb.isEmpty()) measurementUnits[0] else choiceFromDb[0].unit
    var choiceState by remember { mutableStateOf("") }
    Scaffold(topBar = {
        WeatherAppBar(
            title = MainActivity.SETTINGS_SCREEN_APPBAR_TEXT,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController
        ) {
            navController.popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .padding(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .size(500.dp, 500.dp)
                    .clip(shape)
                    .background(Color.LightGray)
                    .padding(50.dp)
            )
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally,
                ) {
                    Text(
                        text = stringResource(id = R.string.settings_change_measure_text)
                    )
                    Spacer(modifier = Modifier.requiredHeight(96.dp))
                    IconToggleButton(
                        checked = !unitToggleState, onCheckedChange = {
                            unitToggleState = !it
                            if (unitToggleState) {
                                choiceState = IMPERIAL
                            } else {
                                choiceState = METRIC
                            }
                        }, modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clip(shape)
                            .padding(10.dp)
                            .background(Color.Magenta.copy(alpha = 0.4f))
                    ) {
                        Text(text = if (unitToggleState) FAHRENHEIT else CELSIUS)
                    }
                    Spacer(modifier = Modifier.requiredHeight(96.dp))
                    Button(
                        onClick = {
                            settingsScreenViewModel.deleteAllUnits()
                            settingsScreenViewModel.insertUnits(Unit(unit = choiceState))
                            Log.d("ChoiceState", choiceState)
                        }, modifier = Modifier
                            .padding(3.dp)
                            .align(CenterHorizontally),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEFBE42))
                    ) {
                        Text(
                            text = stringResource(id = R.string.settings_change_measure_text_save),
                            modifier = Modifier.padding(4.dp),
                            color = Color.White,
                            fontSize = 17.sp
                        )
                    }

                }
            }

        }
    }
}