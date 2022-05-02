package com.farmstead.delivery.util

import android.os.Bundle
import androidx.navigation.NavType
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.domain.Item
import com.google.gson.Gson

class ItemNavType : NavType<Item>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Item? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Item {
        return Gson().fromJson(value, Item::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Item) {
        bundle.putParcelable(key, value)
    }
}