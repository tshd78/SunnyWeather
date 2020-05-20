package com.twist.sunnyweather.logic.network

import com.twist.sunnyweather.SunnyWeatherApplication
import com.twist.sunnyweather.logic.model.DailyResponse
import com.twist.sunnyweather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
interface WeatherService {

    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.Token}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>
}