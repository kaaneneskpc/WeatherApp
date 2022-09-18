package com.example.weatherapp.screen.settingsScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Unit
import com.example.weatherapp.repository.WeatherDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(private val repository: WeatherDBRepository): ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUnits().distinctUntilChanged().collect { listOfUnits ->
                if(listOfUnits.isEmpty()) {
                    // Log.d("UnitListNoFound", "No Units found")
                } else {
                    _unitList.value = listOfUnits
                    // Log.d("UnitList", "${unitList.value}")

                }
            }
        }
    }

    fun insertUnits(unit: Unit)  = viewModelScope.launch { repository.insertUnits(unit) }
    fun deleteUnits(unit: Unit)  = viewModelScope.launch { repository.deleteUnits(unit) }
    fun updateUnits(unit: Unit)  = viewModelScope.launch { repository.updateUnits(unit) }
    fun deleteAllUnits() = viewModelScope.launch { repository.deleteAllUnits()}
}