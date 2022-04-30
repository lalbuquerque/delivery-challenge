package com.farmstead.delivery.domain

import com.google.gson.annotations.SerializedName

data class Price (
    val cents: Long,

    @SerializedName("currency_iso")
    val currencyISO: String
)