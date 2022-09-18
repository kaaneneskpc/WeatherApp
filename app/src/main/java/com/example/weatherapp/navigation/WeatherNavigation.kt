package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.screen.mainScreen.MainScreen
import com.example.weatherapp.screen.SplashScreen
import com.example.weatherapp.screen.aboutScreen.AboutScreen
import com.example.weatherapp.screen.favoritesScreen.FavoritesScreen
import com.example.weatherapp.screen.mainScreen.MainScreenViewModel
import com.example.weatherapp.screen.searchScreen.SearchScreen
import com.example.weatherapp.screen.settingsScreen.SettingsScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val mainScreenRoute = Screens.MainScreen.name
    NavHost(navController = navController, startDestination = Screens.SplashScreen.name) {
        composable(Screens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }
        composable("$mainScreenRoute/{city}", arguments = listOf( navArgument("city") {
            type = NavType.StringType
        })) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("city").let { city ->
                val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
                MainScreen(navController = navController, mainScreenViewModel, city = city)
            }
        }
        composable(Screens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(Screens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(Screens.FavoritesScreen.name) {
            FavoritesScreen(navController = navController)
        }
        composable(Screens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}