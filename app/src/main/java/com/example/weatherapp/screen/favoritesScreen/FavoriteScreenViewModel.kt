package com.example.weatherapp.screen.favoritesScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Favorites
import com.example.weatherapp.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(private val repository: WeatherDBRepository): ViewModel() {

    private val _favList = MutableStateFlow<List<Favorites>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().distinctUntilChanged().collect { listOfFavs ->
                if(listOfFavs.isEmpty()) {
                    Log.d("FavListNotFound", "No favorites found")
                } else {
                    _favList.value = listOfFavs
                    Log.d("FavList", "${favList.value}")
                }
            }
        }
    }

    fun insertFavorites(favorites: Favorites)  = viewModelScope.launch { repository.insertFavorites(favorites) }
    fun deleteFavorites(favorites: Favorites)  = viewModelScope.launch { repository.deleteFavorites(favorites) }
    fun updateFavorites(favorites: Favorites)  = viewModelScope.launch { repository.updateFavorites(favorites) }

}