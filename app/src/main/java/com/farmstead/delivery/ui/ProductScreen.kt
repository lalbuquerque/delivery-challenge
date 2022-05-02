package com.farmstead.delivery.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.farmstead.delivery.domain.Item
import com.farmstead.delivery.domain.formatted
import com.farmstead.delivery.ui.common.AppBarExpendedHeight
import com.farmstead.delivery.ui.common.CollapsingToolbar

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun ProductScreen(item: Item?, navController: NavHostController) {
    val scrollState = rememberLazyListState()

    Box {
        ProductInfoList(scrollState, item!!)
        CollapsingToolbar(scrollState, { navController.navigateUp() }, item.image.url, item.name)
    }
}

@Composable
fun ProductInfoList(scrollState: LazyListState, item: Item) {
    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ProductInfoCard("ID", "#" + item.id)
                Spacer(modifier = Modifier.height(10.dp))
                ProductInfoCard("Price", item.hubItems.first { it.hubID == 2L }.price.formatted())
                Spacer(modifier = Modifier.height(10.dp))
                ProductInfoCard("Display size and measure", item.displaySizeAndMeasure)
                Spacer(modifier = Modifier.height(10.dp))
                ProductInfoCard("ID", "#" + item.id)
                Spacer(modifier = Modifier.height(10.dp))
                ProductInfoCard("Price", item.hubItems.first { it.hubID == 2L }.price.formatted())
                Spacer(modifier = Modifier.height(10.dp))
                ProductInfoCard("Display size and measure", item.displaySizeAndMeasure)
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}

@Composable
fun ProductInfoCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
            .height(75.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subtitle,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
        }
    }
}