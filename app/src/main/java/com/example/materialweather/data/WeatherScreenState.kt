package com.example.materialweather.data

data class WeatherScreenState(
    val screenData: WeatherResponse? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null,
)