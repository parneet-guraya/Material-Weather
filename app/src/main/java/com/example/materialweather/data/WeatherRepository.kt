package com.example.materialweather.data

import com.example.materialweather.Response

class WeatherRepository(private val remoteWeatherDataSource: RemoteWeatherDataSource) {


    suspend fun getCurrentWeather(location: String): Response<WeatherResponse> {
       return try {
            val weatherResponse = remoteWeatherDataSource.getCurrentWeather(location)
            Response.Success(weatherResponse)
        } catch (throwable: Throwable) {
            Response.Error(throwable = throwable)
        }
    }

    suspend fun getHourlyForecast(location: String): Response<WeatherResponse> {
        return try {
            val weatherResponse = remoteWeatherDataSource.getHourlyForecast(location)
            Response.Success(weatherResponse)
        } catch (throwable: Throwable) {
            Response.Error(throwable = throwable)
        }
    }

}