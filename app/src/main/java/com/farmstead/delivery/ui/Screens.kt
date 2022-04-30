package com.farmstead.delivery.ui

sealed class Screens(val title: String) {
    object Delivery : Screens("delivery_screen")
}