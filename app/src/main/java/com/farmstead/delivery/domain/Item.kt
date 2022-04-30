package com.farmstead.delivery.domain

import com.google.gson.annotations.SerializedName

data class Item (
    val id: Long,
    val name: String,
    val image: Image,

    @SerializedName("display_size_and_measure")
    val displaySizeAndMeasure: String,

    @SerializedName("hub_items")
    val hubItems: List<HubItem>
)
