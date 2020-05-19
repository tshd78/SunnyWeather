package com.twist.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/*"status": "ok",
"query": "北京",
"places": [
{
    "id": "6e8861ded5d03c5fdfe7a56526af08ecb433a575",
    "location": {
    "lat": 39.9041999,
    "lng": 116.4073963
},
    "place_id": "g-6e8861ded5d03c5fdfe7a56526af08ecb433a575",
    "name": "北京市",
    "formatted_address": "中国北京市"
},*/
data class PlaceResponse(val status: String, val places: List<Place>)

data class Place(val name: String, val location: Location,
                 @SerializedName("formatted_address") val address: String) // JSON 和 Kotlin 之间字段建立映射关系

data class Location(val lng: String, val lat: String)