package com.farmstead.delivery.network

import com.farmstead.delivery.domain.Deliveries
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliveryService {

    @GET("api/v1/store/deliveries/upcoming")
    suspend fun getUpcomingDeliveries(@Query("hub_id") hubId: String, @Query("ak") ak: String): Deliveries
}