package com.example.materialweather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.materialweather.data.RemoteWeatherDataSource
import com.example.materialweather.data.RetrofitClient
import com.example.materialweather.data.WeatherRepository
import com.example.materialweather.data.WeatherScreenState
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val weatherRepository =
        WeatherRepository(RemoteWeatherDataSource(RetrofitClient.weatherApi))

    var screenState by mutableStateOf(WeatherScreenState())
        private set


    fun getCurrentWeather(location: String) {
        viewModelScope.launch {
            screenState = screenState.copy(loading = true)
            val weatherResponse = weatherRepository.getCurrentWeather(location)
            screenState = when (weatherResponse) {
                is Response.Success -> screenState.copy(screenData = weatherResponse.data, loading = false)

                is Response.Error -> screenState.copy(
                    errorMessage = weatherResponse.throwable?.message
                        ?: "Unknown Error occurred", loading = false
                )
            }
        }
    }

}