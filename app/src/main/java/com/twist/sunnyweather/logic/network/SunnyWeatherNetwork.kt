package com.twist.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
object SunnyWeatherNetwork {

    private val weatherService = ServiceCreator.create<WeatherService>()

    suspend fun getDailyWeather(lng: String, lat: String) =
        weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String, lat: String) =
        weatherService.getRealtimeWeather(lng, lat).await()


    // 接口的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>() // 泛型实化

    // 挂起函数
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    // 简化匿名类写法
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation -> // 前提是在协程作用域或挂起函数中调用，将当前协程挂起，在普通的线程执行 Lambda 表达式中的代码，"简化回调写法"
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body) // 协程恢复执行
                    else continuation.resumeWithException(  // 协程恢复执行
                        RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}