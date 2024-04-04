package com.example.materialweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.materialweather.data.RemoteWeatherDataSource
import com.example.materialweather.data.RetrofitClient
import com.example.materialweather.data.WeatherRepository
import com.example.materialweather.data.WeatherScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherRepository =
        WeatherRepository(RemoteWeatherDataSource(RetrofitClient.weatherApi))

    private val _uiState = MutableStateFlow<WeatherScreenState>(WeatherScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        val initLocation = "London"
        getCurrentWeather(initLocation)
        getHourlyForecast(initLocation)
    }

    fun getCurrentWeather(location: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val weatherResponse = weatherRepository.getCurrentWeather(location)
            when (weatherResponse) {
                is Response.Success -> {
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        screenData = weatherResponse.data,
                        errorMessage = null
                    )
                    println("Weather Response: ${weatherResponse.data}")
                }

                is Response.Error -> _uiState.update {
                    WeatherScreenState(
                        screenData = null,
                        loading = false,
                        errorMessage = weatherResponse.throwable.message
                    )
                }
            }
        }
    }

    fun getHourlyForecast(location: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(loading = true) }
            val response = weatherRepository.getHourlyForecast(location)
            when (response) {
                is Response.Success -> {
                    _uiState.update {
                        it.copy(loading = false, screenData = response.data, errorMessage = null)
                    }
                }

                is Response.Error -> _uiState.update {
                    it.copy(
                        loading = false,
                        screenData = null,
                        errorMessage = response.throwable.message
                    )
                }
            }
        }
    }

}