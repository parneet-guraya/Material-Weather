package com.example.materialweather.data

data class WeatherScreenState(
    val screenData: Any? = null,
    val loading: Boolean = false,
    val errorMessage: String? = null,
)