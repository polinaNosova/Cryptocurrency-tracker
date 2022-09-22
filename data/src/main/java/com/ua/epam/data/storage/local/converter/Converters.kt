package com.ua.epam.data.storage.local.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromListOfFloatToString(sparklineInSevenD: ArrayList<Float>): String {
        return GsonBuilder().create().toJson(sparklineInSevenD)
    }

    @TypeConverter
    fun fromStringToListOfFloat(string: String): ArrayList<Float> {
        val list = object : TypeToken<ArrayList<Float>>() {}.type
        return GsonBuilder().create().fromJson(string, list)
    }
}
