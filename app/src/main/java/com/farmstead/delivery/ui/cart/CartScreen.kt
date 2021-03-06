package com.farmstead.delivery.ui.cart

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.ui.Screens
import com.google.gson.Gson


@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun CartScreen(delivery: Delivery?, navController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Order #${delivery?.id.toString()}")
                },
                elevation = 8.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Rounded.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            OrderItemVerticalCollection(delivery?.orderItems ?: emptyList()) { orderItem ->
                val itemJson = Uri.encode(Gson().toJson(orderItem.item))
                navController.navigate(
                    Screens.Product.title.replace("{${Screens.ITEM}}", itemJson))
            }
        })
}