package com.farmstead.delivery.ui.delivery

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.farmstead.delivery.R
import com.farmstead.delivery.domain.CurrentDelivery
import com.farmstead.delivery.domain.toCurrentDelivery
import com.farmstead.delivery.persistency.LocalDataWrapper
import com.farmstead.delivery.ui.Content
import com.farmstead.delivery.ui.Screens
import com.farmstead.delivery.ui.Screens.Companion.DELIVERY
import com.farmstead.delivery.viewmodel.DeliveryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun DeliveryListScreen(
    context: Context,
    navController: NavHostController,
    deliveryViewModel: DeliveryViewModel = hiltViewModel(),
    localDataWrapper: LocalDataWrapper<CurrentDelivery>
) {
    val scope = rememberCoroutineScope()
    val appBarPlaceholderText = stringResource(id = R.string.label_delivery)
    val appBarText = remember { mutableStateOf(appBarPlaceholderText) }

    LaunchedEffect(true){
        scope.launch {
            localDataWrapper.getAsFlow("")?.collect {
                val newAppBarText = it.label.ifEmpty { appBarPlaceholderText }
                appBarText.value = newAppBarText
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val text by appBarText
                        Text(text)
                    }
                },
                elevation = 8.dp,
            )
        },
        content = {
            Content(viewModel = deliveryViewModel) { deliveries ->
                DeliveryVerticalCollection(deliveries,
                    onDeliveryClick = { delivery ->
                        scope.launch {
                            localDataWrapper.save(delivery.toCurrentDelivery())
                        }
                }, onOrderItemClick = { delivery ->
                    val deliveryJson = Uri.encode(Gson().toJson(delivery))
                    navController.navigate(
                        Screens.Cart.title.replace("{$DELIVERY}",
                        deliveryJson))
                })
            }
        })
}