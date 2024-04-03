package com.example.materialweather.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "location") val location: Location?,
    @Json(name = "current") val current: Current?
)

data class Location(
    @Json(name = "name") val name: String?,
    @Json(name = "region") val region: String?,
    @Json(name = "country") val country: String?,
    @Json(name = "lat") val lat: Double?,
    @Json(name = "lon") val lon: Double?,
    @Json(name = "tz_id") val tzId: String?,
    @Json(name = "localtime_epoch") val localtimeEpoch: Long?,
    @Json(name = "localtime") val localtime: String?
)

data class Current(
    @Json(name = "last_updated_epoch") val lastUpdatedEpoch: Long?,
    @Json(name = "last_updated") val lastUpdated: String?,
    @Json(name = "temp_c") val tempC: Double?,
    @Json(name = "temp_f") val tempF: Double?,
    @Json(name = "is_day") val isDay: Int?,
    @Json(name = "condition") val condition: Condition?,
    @Json(name = "wind_mph") val windMph: Double?,
    @Json(name = "wind_kph") val windKph: Double?,
    @Json(name = "wind_degree") val windDegree: Int?,
    @Json(name = "wind_dir") val windDir: String?,
    @Json(name = "pressure_mb") val pressureMb: Double?,
    @Json(name = "pressure_in") val pressureIn: Double?,
    @Json(name = "precip_mm") val precipMm: Double?,
    @Json(name = "precip_in") val precipIn: Double?,
    @Json(name = "humidity") val humidity: Int?,
    @Json(name = "cloud") val cloud: Int?,
    @Json(name = "feelslike_c") val feelslikeC: Double?,
    @Json(name = "feelslike_f") val feelslikeF: Double?,
    @Json(name = "vis_km") val visKm: Double?,
    @Json(name = "vis_miles") val visMiles: Double?,
    @Json(name = "uv") val uv: Double?,
    @Json(name = "gust_mph") val gustMph: Double?,
    @Json(name = "gust_kph") val gustKph: Double?
)

data class Condition(
    @Json(name = "text") val text: String?,
    @Json(name = "icon") val icon: String?,
    @Json(name = "code") val code: Int?
)