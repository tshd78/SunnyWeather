package com.twist.sunnyweather.logic.model

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */

// 将 Realtime 和 Daily 对象封装起来
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)