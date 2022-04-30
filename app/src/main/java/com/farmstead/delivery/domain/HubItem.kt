package com.farmstead.delivery.domain

import com.google.gson.annotations.SerializedName

data class HubItem (
    @SerializedName("hub_id")
    val hubID: Long,

    val price: Price,

    @SerializedName("most_recent_manufacturer")
    val mostRecentManufacturer: MostRecentManufacturer
)