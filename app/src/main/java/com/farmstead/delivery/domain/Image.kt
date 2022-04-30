package com.farmstead.delivery.domain

import com.google.gson.annotations.SerializedName

data class Image (
    val url: String,

    @SerializedName("thumb_400")
    val thumb: Thumb
)