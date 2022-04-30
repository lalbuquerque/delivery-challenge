package com.farmstead.delivery.persistency.room.delivery.converter

import androidx.room.TypeConverter
import com.farmstead.delivery.domain.OrderItem
import com.google.gson.Gson

class OrderItemConverter {

    @TypeConverter
    fun jsonToList(value: String): List<OrderItem> =
        Gson().fromJson(value, Array<OrderItem>::class.java).toList()

    @TypeConverter
    fun listToJson(value: List<OrderItem?>) = Gson().toJson(value.filterNotNull())
}