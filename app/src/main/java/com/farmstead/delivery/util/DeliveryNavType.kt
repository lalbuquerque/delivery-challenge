package com.farmstead.delivery.util

import android.os.Bundle
import androidx.navigation.NavType
import com.farmstead.delivery.domain.Delivery
import com.google.gson.Gson

class DeliveryNavType : NavType<Delivery>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Delivery? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Delivery {
        return Gson().fromJson(value, Delivery::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Delivery) {
        bundle.putParcelable(key, value)
    }
}