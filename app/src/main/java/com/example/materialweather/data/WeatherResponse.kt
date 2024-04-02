package com.example.materialweather.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "location") val location: Location?,
    @field:Json(name = "current") val current: Current?
)

data class Location(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "region") val region: String?,
    @field:Json(name = "country") val country: String?,
    @field:Json(name = "lat") val lat: Double?,
    @field:Json(name = "lon") val lon: Double?,
    @field:Json(name = "tz_id") val tzId: String?,
    @field:Json(name = "localtime_epoch") val localtimeEpoch: Long?,
    @field:Json(name = "localtime") val localtime: String?
)

data class Current(
    @field:Json(name = "last_updated_epoch") val lastUpdatedEpoch: Long?,
    @field:Json(name = "last_updated") val lastUpdated: String?,
    @field:Json(name = "temp_c") val tempC: Double?,
    @field:Json(name = "temp_f") val tempF: Double?,
    @field:Json(name = "is_day") val isDay: Int?,
    @field:Json(name = "condition") val condition: Condition?,
    @field:Json(name = "wind_mph") val windMph: Double?,
    @field:Json(name = "wind_kph") val windKph: Double?,
    @field:Json(name = "wind_degree") val windDegree: Int?,
    @field:Json(name = "wind_dir") val windDir: String?,
    @field:Json(name = "pressure_mb") val pressureMb: Double?,
    @field:Json(name = "pressure_in") val pressureIn: Double?,
    @field:Json(name = "precip_mm") val precipMm: Double?,
    @field:Json(name = "precip_in") val precipIn: Double?,
    @field:Json(name = "humidity") val humidity: Int?,
    @field:Json(name = "cloud") val cloud: Int?,
    @field:Json(name = "feelslike_c") val feelslikeC: Double?,
    @field:Json(name = "feelslike_f") val feelslikeF: Double?,
    @field:Json(name = "vis_km") val visKm: Double?,
    @field:Json(name = "vis_miles") val visMiles: Double?,
    @field:Json(name = "uv") val uv: Double?,
    @field:Json(name = "gust_mph") val gustMph: Double?,
    @field:Json(name = "gust_kph") val gustKph: Double?
)

data class Condition(
    @field:Json(name = "text") val text: String?,
    @field:Json(name = "icon") val icon: String?,
    @field:Json(name = "code") val code: Int?
)