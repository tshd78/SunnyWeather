package com.twist.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.twist.sunnyweather.SunnyWeatherApplication
import com.twist.sunnyweather.logic.model.Place

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
object PlaceDAO {

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = SunnyWeatherApplication.context
        .getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)
}