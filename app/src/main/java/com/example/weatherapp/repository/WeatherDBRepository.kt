package com.example.weatherapp.repository

import com.example.weatherapp.data.WeatherDao
import com.example.weatherapp.model.Favorites
import com.example.weatherapp.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDBRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavorites(): Flow<List<Favorites>> = weatherDao.getFavorites()
    suspend fun getFavoritesById(city: String) = weatherDao.getFavById(city)
    suspend fun insertFavorites(favorites: Favorites) = weatherDao.insertFavorites(favorites)
    suspend fun updateFavorites(favorites: Favorites) = weatherDao.updateFavorites(favorites)
    suspend fun deleteFavorites(favorites: Favorites) = weatherDao.deleteFavorites(favorites)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()

    fun getUnits(): Flow<List<Unit>> = weatherDao.getUnits()
    suspend fun insertUnits(unit: Unit) = weatherDao.insertUnits(unit)
    suspend fun updateUnits(unit: Unit) = weatherDao.updateUnits(unit)
    suspend fun deleteUnits(unit: Unit) = weatherDao.deleteUnits(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
}