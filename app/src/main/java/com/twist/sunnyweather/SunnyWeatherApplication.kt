package com.twist.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.google.gson.reflect.TypeToken

// 获取全局 Context 对象
class SunnyWeatherApplication : Application() {

    companion object {

        const val Token = "BQurQTg9xQAGRItj"

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}