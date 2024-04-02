package com.example.materialweather.data

import com.example.materialweather.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    val weatherApi: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(WeatherApi::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return  OkHttpClient.Builder()
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val modifiedUrl = request.url().newBuilder().addQueryParameter("key",BuildConfig.weatherApiKey).build()
                    val modifiedRequest = request.newBuilder().url(modifiedUrl).build()
                    return chain.proceed(modifiedRequest)
                }
            }).build()
    }

    private const val BASE_URL = "https://api.weatherapi.com/v1/"

}