package com.twist.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */

// 定义在内部，防止出现和其他接口的模型数据类有同名冲突的情况
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String, val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}