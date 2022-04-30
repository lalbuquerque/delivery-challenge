package com.farmstead.delivery.persistency.room.delivery

import androidx.room.Entity
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.domain.OrderItem

@Entity(tableName = "delivery", primaryKeys = ["id"])
data class DbDelivery(
    val id: Long,
    val scheduledStart: String,
    val scheduledEnd: String,
    val deliveryType: String,
    val deliveryDayAbbreviated: String,
    val deliveryDate: String,
    val deliveryTimeRange: String,
    val deliveryState: String,
    val orderItemsCount: Long,
    val orderItemImageUrls: List<String>,
    val orderItems: List<OrderItem>,
)

fun DbDelivery.isPending() = deliveryState == "instant_pending"

fun MutableList<DbDelivery>.asDomainModel(): MutableList<Delivery> {
    val (pending, rest) = this.partition {
        it.isPending()
    }
    val pendingDeliveries = pending.sortedBy { it.id }
    val otherDeliveries = rest.sortedBy { it.id }
    val sortedDeliveries = pendingDeliveries + otherDeliveries

    return sortedDeliveries.map {
        Delivery(id = it.id,
            scheduledStart = it.scheduledStart,
            scheduledEnd = it.scheduledEnd,
            deliveryType = it.deliveryType,
            deliveryDayAbbreviated = it.deliveryDayAbbreviated,
            deliveryDate = it.deliveryDate,
            deliveryTimeRange = it.deliveryTimeRange,
            deliveryState = it.deliveryState,
            orderItemsCount = it.orderItemsCount,
            orderItemImageUrls = it.orderItemImageUrls,
            orderItems = it.orderItems)
    }.toMutableList()
}