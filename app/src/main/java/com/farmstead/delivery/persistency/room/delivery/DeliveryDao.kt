package com.farmstead.delivery.persistency.room.delivery

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeliveryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(delivery: MutableList<DbDelivery>?)

    @Query("SELECT * FROM delivery")
    suspend fun getDeliveries(): MutableList<DbDelivery>?
}