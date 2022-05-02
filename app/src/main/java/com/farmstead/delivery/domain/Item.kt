package com.farmstead.delivery.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Item (
    val id: Long,
    val name: String,
    val image: @RawValue Image,

    @SerializedName("display_size_and_measure")
    val displaySizeAndMeasure: String,

    @SerializedName("hub_items")
    val hubItems: @RawValue List<HubItem>
) : Parcelable
