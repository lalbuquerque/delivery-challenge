package com.farmstead.delivery.ui

sealed class Screens(val title: String) {
    object Delivery : Screens("delivery_screen")
    object Cart : Screens("cart_screen/{$DELIVERY}")

    companion object {
        const val DELIVERY = "delivery"
    }
}