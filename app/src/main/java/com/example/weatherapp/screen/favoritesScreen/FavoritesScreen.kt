package com.example.weatherapp.screen.favoritesScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.model.Favorites
import com.example.weatherapp.navigation.Screens
import com.example.weatherapp.widgets.WeatherAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(navController: NavController, favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()) {
    Scaffold(topBar = {
        WeatherAppBar(
            title= MainActivity.FAVORITES_SCREEN_APPBAR_TEXT,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
            navController = navController) {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                val list = favoriteScreenViewModel.favList.collectAsState().value
                LazyColumn {
                    items(items = list) {
                        CityRow(it, navController = navController, favoriteScreenViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(favorites: Favorites, navController: NavController, favoriteScreenViewModel: FavoriteScreenViewModel) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(3.dp)
        .height(50.dp)
        .clickable { navController.navigate(Screens.MainScreen.name + "/${favorites.city}")
        }, shape = CircleShape.copy(topEnd = CornerSize(6.dp)), color = Color((0xFFB2DFDB))) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = favorites.city, modifier = Modifier.padding(start = 4.dp))
            Surface(modifier = Modifier.padding(0.dp), shape = CircleShape, color =Color(0xFFD1E3E1)) {
                Text(text = favorites.country, modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.caption)
            }
            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "delete", modifier = Modifier.clickable {
                favoriteScreenViewModel.deleteFavorites(favorites)
            }, tint = Color.Red.copy(alpha = 0.3f))
        }
    }
}
