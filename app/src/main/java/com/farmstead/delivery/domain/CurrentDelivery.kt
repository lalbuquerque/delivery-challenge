package com.farmstead.delivery.domain

data class CurrentDelivery(val id: Long, val label: String)

fun Delivery.toCurrentDelivery() =
    CurrentDelivery(this.id, this.getBasicInfo())