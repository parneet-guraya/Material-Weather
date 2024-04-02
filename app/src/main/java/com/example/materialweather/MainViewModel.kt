package com.example.materialweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.materialweather.data.RemoteWeatherDataSource
import com.example.materialweather.data.RetrofitClient
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val remoteWeatherDataSource = RemoteWeatherDataSource(RetrofitClient.weatherApi)

    fun getCurrentWeather(location: String) {
        viewModelScope.launch {
            val weatherResponse = remoteWeatherDataSource.getCurrentWeather(location)
            println(weatherResponse)
        }
    }

}