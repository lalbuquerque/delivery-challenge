package com.farmstead.delivery.domain

import android.os.Parcelable
import com.farmstead.delivery.persistency.room.delivery.DbDelivery
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Delivery (
    val id: Long,

    @SerializedName("scheduled_start")
    val scheduledStart: String,

    @SerializedName("scheduled_end")
    val scheduledEnd: String,

    @SerializedName("delivery_type")
    val deliveryType: String,

    @SerializedName("delivery_day_abbreviated")
    val deliveryDayAbbreviated: String,

    @SerializedName("delivery_date")
    val deliveryDate: String,

    @SerializedName("delivery_time_range")
    val deliveryTimeRange: String,

    @SerializedName("delivery_state")
    val deliveryState: String,

    @SerializedName("order_items_count")
    val orderItemsCount: Long,

    @SerializedName("order_item_image_urls")
    val orderItemImageUrls: List<String>,

    @SerializedName("order_items")
    val orderItems: @RawValue List<OrderItem>,
) : Parcelable

fun Delivery.getBasicInfo() = "${this.deliveryDayAbbreviated} ${this.deliveryDate} ${this.deliveryTimeRange}"
fun Delivery.isPending() = deliveryState == "instant_pending"

fun MutableList<Delivery>.asDatabaseModel(): MutableList<DbDelivery> {
    return this.map {
        DbDelivery(id = it.id,
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