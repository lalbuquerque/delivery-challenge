package com.farmstead.delivery.persistency.room.delivery.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class StringListConverter {

    @TypeConverter
    fun jsonToList(value: String): List<String> =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<String?>) = Gson().toJson(value.filterNotNull())
}