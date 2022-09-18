package com.example.weatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.Favorites
import com.example.weatherapp.model.Unit

@Database(entities = [Favorites::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao

}