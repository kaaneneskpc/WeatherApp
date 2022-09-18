package com.example.weatherapp.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherapp.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import com.example.weatherapp.navigation.Screens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    val animation = remember { Animatable(0f) }
    val defaultCity = "San Diego"
    
    LaunchedEffect(key1 = true, block = {animation.animateTo(targetValue = 0.9f, animationSpec = tween(
        durationMillis = 800, easing =  {
            OvershootInterpolator(8f).getInterpolation(it)
        }
    ))
        delay(2000L)
        navController.navigate(Screens.MainScreen.name + "/$defaultCity")
    })
    
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(animation.value),
        shape = CircleShape, color = Color.White, border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
        Column(modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.sun), contentDescription = "sunnyIcon", modifier = Modifier.size(95.dp), contentScale = ContentScale.Fit)
            Text(text = stringResource(id = R.string.splash_screen_text), style = MaterialTheme.typography.h5, color = Color.LightGray)
        }
    }
}