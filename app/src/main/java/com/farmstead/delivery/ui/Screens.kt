package com.farmstead.delivery.ui

sealed class Screens(val title: String) {
    object Delivery : Screens("delivery_screen")
    object Cart : Screens("cart_screen/{$DELIVERY}")
    object Product : Screens("product_screen/{$ITEM}")

    companion object {
        const val DELIVERY = "delivery"
        const val ITEM = "item"
    }
}