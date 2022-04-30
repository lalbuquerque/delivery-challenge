package com.farmstead.delivery.persistency.room.delivery.converter

import androidx.room.TypeConverter
import com.farmstead.delivery.persistency.room.delivery.DbDelivery
import com.google.gson.Gson

class DbDeliveryConverter {

    @TypeConverter
    fun jsonToList(value: String): List<DbDelivery> =
        Gson().fromJson(value, Array<DbDelivery>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<DbDelivery?>) = Gson().toJson(value.filterNotNull())
}