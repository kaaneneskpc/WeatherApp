package com.example.weatherapp.data

import androidx.room.*
import com.example.weatherapp.model.Favorites
import com.example.weatherapp.model.Unit
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {
    @Query("SELECT * from fav_tbl")
    fun getFavorites(): Flow<List<Favorites>>

    @Query("SELECT * from fav_tbl where city =:city")
    suspend fun getFavById(city: String): Favorites

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favorites: Favorites)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorites(favorites: Favorites)

    @Query("DELETE  from fav_tbl")
    suspend fun deleteAllFavorites()

    @Delete
    suspend fun deleteFavorites(favorites: Favorites)

    //Unit

    @Query("SELECT * from settings_tbl")
    fun getUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnits(unit: Unit)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnits(unit: Unit)

    @Query("DELETE  from settings_tbl")
    suspend fun deleteAllUnits()

    @Delete
    suspend fun deleteUnits(unit: Unit)


}