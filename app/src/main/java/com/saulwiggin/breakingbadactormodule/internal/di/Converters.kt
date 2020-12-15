package com.saulwiggin.breakingbadactormodule.internal.di

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        Log.i("BreakingBad", "fromString: ${value}")
        value?.let {
            val listType = object : TypeToken<List<String>>() {}.type
            Log.i("BreakingBad", "fromStringL: ${listType}")
            return Gson().fromJson(value, listType)
        }
        return emptyList()
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        list?.let {
            val gson = Gson()
            return gson.toJson(list)
        }
        return null
    }
}