package com.twist.sunnyweather.logic

import androidx.lifecycle.liveData
import com.twist.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import com.twist.sunnyweather.logic.model.Place

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result as Result<List<Place>>)

    }
}