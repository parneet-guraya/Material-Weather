package com.example.materialweather.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "location") val location: Location?,
    @Json(name = "current") val current: Current?,
    @Json(name = "forecast") val forecast: Forecast?
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

data class Forecast(
    @Json(name = "forecastday") val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    @Json(name = "date") val date: String?,
    @Json(name = "date_epoch") val dateEpoch: Long,
    @Json(name = "day") val day: Day,
    @Json(name = "astro") val astro: Astro,
    @Json(name = "hour") val hour: List<Hour>
)

data class Day(
    @Json(name = "maxtemp_c") val maxTempC: Double?,
    @Json(name = "maxtemp_f") val maxTempF: Double?,
    @Json(name = "mintemp_c") val minTempC: Double?,
    @Json(name = "mintemp_f") val minTempF: Double?,
    @Json(name = "avgtemp_c") val avgTempC: Double?,
    @Json(name = "avgtemp_f") val avgTempF: Double?,
    @Json(name = "maxwind_mph") val maxWindMph: Double?,
    @Json(name = "maxwind_kph") val maxWindKph: Double?,
    @Json(name = "totalprecip_mm") val totalPrecipMm: Double?,
    @Json(name = "totalprecip_in") val totalPrecipIn: Double?,
    @Json(name = "totalsnow_cm") val totalSnowCm: Double?,
    @Json(name = "avgvis_km") val avgVisKm: Double?,
    @Json(name = "avgvis_miles") val avgVisMiles: Double?,
    @Json(name = "avghumidity") val avgHumidity: Int?,
    @Json(name = "daily_will_it_rain") val dailyWillItRain: Int?,
    @Json(name = "daily_chance_of_rain") val dailyChanceOfRain: Int?,
    @Json(name = "daily_will_it_snow") val dailyWillItSnow: Int?,
    @Json(name = "daily_chance_of_snow") val dailyChanceOfSnow: Int?,
    @Json(name = "condition") val condition: Condition,
    @Json(name = "uv") val uv: Double?
)

data class Astro(
    @Json(name = "sunrise") val sunrise: String?,
    @Json(name = "sunset") val sunset: String?,
    @Json(name = "moonrise") val moonrise: String?,
    @Json(name = "moonset") val moonset: String?,
    @Json(name = "moon_phase") val moonPhase: String?,
    @Json(name = "moon_illumination") val moonIllumination: Int?,
    @Json(name = "is_moon_up") val isMoonUp: Int?,
    @Json(name = "is_sun_up") val isSunUp: Int?
)

@JsonClass(generateAdapter = true)
data class Hour(
    @Json(name = "time_epoch") val timeEpoch: Long,
    @Json(name = "time") val time: String?,
    @Json(name = "temp_c") val tempC: Double?,
    @Json(name = "temp_f") val tempF: Double?,
    @Json(name = "is_day") val isDay: Int?,
    @Json(name = "condition") val condition: Condition,
    @Json(name = "wind_mph") val windMph: Double?,
    @Json(name = "wind_kph") val windKph: Double?,
    @Json(name = "wind_degree") val windDegree: Int?,
    @Json(name = "wind_dir") val windDir: String?,
    @Json(name = "pressure_mb") val pressureMb: Double?,
    @Json(name = "pressure_in") val pressureIn: Double?,
    @Json(name = "precip_mm") val precipMm: Double?,
    @Json(name = "precip_in") val precipIn: Double?,
    @Json(name = "snow_cm") val snowCm: Double?,
    @Json(name = "humidity") val humidity: Int?,
    @Json(name = "cloud") val cloud: Int?,
    @Json(name = "feelslike_c") val feelsLikeC: Double?,
    @Json(name = "feelslike_f") val feelsLikeF: Double?,
    @Json(name = "windchill_c") val windChillC: Double?,
    @Json(name = "windchill_f") val windChillF: Double?,
    @Json(name = "heatindex_c") val heatIndexC: Double?,
    @Json(name = "heatindex_f") val heatIndexF: Double?,
    @Json(name = "dewpoint_c") val dewPointC: Double?,
    @Json(name = "dewpoint_f") val dewPointF: Double?,
    @Json(name = "will_it_rain") val willItRain: Int?,
    @Json(name = "chance_of_rain") val chanceOfRain: Int?,
    @Json(name = "will_it_snow") val willItSnow: Int?,
    @Json(name = "chance_of_snow") val chanceOfSnow: Int?,
    @Json(name = "vis_km") val visKm: Double?,
    @Json(name = "vis_miles") val visMiles: Double?,
    @Json(name = "gust_mph") val gustMph: Double?,
    @Json(name = "gust_kph") val gustKph: Double?,
    @Json(name = "uv") val uv: Double?
)
