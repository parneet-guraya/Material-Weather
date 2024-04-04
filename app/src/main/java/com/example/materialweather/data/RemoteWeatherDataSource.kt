package com.example.materialweather.data

class RemoteWeatherDataSource(private val weatherApi: WeatherApi) {

    suspend fun getCurrentWeather(query:String): WeatherResponse {
        return weatherApi.getCurrentWeather(query)
    }


    suspend fun getForecast(location: String, days: Int): WeatherResponse {
        return weatherApi.getForecast(location, days)
    }

    suspend fun getHourlyForecast(location: String): WeatherResponse =
        getForecast(location, days = 1)
}