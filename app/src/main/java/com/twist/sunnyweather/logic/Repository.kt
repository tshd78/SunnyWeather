package com.twist.sunnyweather.logic

import android.content.Context
import androidx.lifecycle.liveData
import com.twist.sunnyweather.logic.dao.PlaceDAO
import com.twist.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import com.twist.sunnyweather.logic.model.Place
import com.twist.sunnyweather.logic.model.Weather
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
object Repository {

    fun savePlace(place: Place) = PlaceDAO.savePlace(place)

    fun getSavedPlace() = PlaceDAO.getSavedPlace()

    fun isPlaceSaved() = PlaceDAO.isPlaceSaved()

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {// 阻塞
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(lng, lat)
            }
            // 并行
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    // 由于使用协程来简化网络回调写法，导致每个网络接口都可能会抛出异常，统一入口函数进行封装，一次 try catch 调用
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) = // suspend 表示 Lambda 表达式中的代码也是拥有挂起函数上下文的
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}