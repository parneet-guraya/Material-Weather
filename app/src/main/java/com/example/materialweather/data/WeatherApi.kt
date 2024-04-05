package com.example.materialweather.data

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    suspend fun getCurrentWeather(@Query("q") query: String): WeatherResponse

    // free api only returns 3 days of forecast including current day.
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") location: String,
        @Query("days") forecastDays: Int
    ): WeatherResponse
}