package com.example.materialweather.data

class RemoteWeatherDataSource(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(query:String): WeatherResponse {
        return weatherApi.getCurrentWeather(query)
    }
companion object{
}
}