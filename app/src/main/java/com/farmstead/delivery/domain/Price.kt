package com.farmstead.delivery.domain

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat
import java.util.*

data class Price (
    val cents: Long,

    @SerializedName("currency_iso")
    val currencyISO: String
)

fun Price.formatted(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return if (this.cents == -1L) "$ --" else formatter.format(this.cents / 100.0)
}