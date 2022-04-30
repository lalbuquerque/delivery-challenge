package com.farmstead.delivery.persistency.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farmstead.delivery.persistency.room.delivery.DeliveryDao
import com.farmstead.delivery.persistency.room.delivery.DbDelivery
import com.farmstead.delivery.persistency.room.delivery.converter.DbDeliveryConverter
import com.farmstead.delivery.persistency.room.delivery.converter.OrderItemConverter
import com.farmstead.delivery.persistency.room.delivery.converter.StringListConverter

@Database(entities = [DbDelivery::class], version = 1, exportSchema = false)
@TypeConverters(DbDeliveryConverter::class, OrderItemConverter::class, StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun deliveryDao(): DeliveryDao
}