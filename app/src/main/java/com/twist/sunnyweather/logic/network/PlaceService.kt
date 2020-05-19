package com.twist.sunnyweather.logic.network

import com.twist.sunnyweather.SunnyWeatherApplication
import com.twist.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.Token}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}