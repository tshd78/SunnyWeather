package com.twist.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.twist.sunnyweather.logic.Repository
import com.twist.sunnyweather.logic.model.Place

/**
 * File description
 *
 * @author twist
 * @email twistonidea@gmail.com
 *
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()

    // 对显示的城市数据进行缓存，放到 ViewModel 中，保证在手机屏幕旋转时不会丢失
    val placeList = ArrayList<Place>()

    // 转换函数在调用函数执行后执行
    val placeLiveData =
        Transformations.switchMap(searchLiveData) { query -> Repository.searchPlaces(query) }

    // 调用函数
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved() = Repository.isPlaceSaved()
}