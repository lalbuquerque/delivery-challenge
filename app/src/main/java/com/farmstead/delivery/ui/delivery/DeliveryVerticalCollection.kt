package com.farmstead.delivery.ui.delivery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.farmstead.delivery.R
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.domain.getBasicInfo
import com.farmstead.delivery.domain.isPending
import com.farmstead.delivery.ui.common.ChipLabel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DeliveryVerticalCollection(
    deliveries: List<Delivery>,
    onDeliveryClick: (Delivery) -> Unit,
    onOrderItemClick: (Delivery) -> Unit
) {
    LazyColumn {
        items(
            items = deliveries,
            itemContent = { delivery ->
                VerticalListItem(delivery = delivery,
                    onItemClick = onDeliveryClick,
                    onOrderItemClick = onOrderItemClick)
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    delivery: Delivery,
    onItemClick: (Delivery) -> Unit,
    onOrderItemClick: (Delivery) -> Unit
) {
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .clickable(onClick = { onItemClick(delivery) }),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 4.dp)) {
                Text(
                    text = delivery.getBasicInfo(),
                    style = typography.subtitle1,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                if (delivery.isPending()) {
                    ChipLabel(
                        text = stringResource(id = R.string.chip_pending),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            OrderItemsRow(delivery, onOrderItemClick)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun OrderItemsRow(delivery: Delivery, onClick: (Delivery) -> Unit) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.horizontalScroll(rememberScrollState())) {

        delivery.orderItemImageUrls.take(4).forEachIndexed { index, url ->
            GlideImage(imageModel = url, modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clickable { onClick(delivery) })

            Spacer(modifier = Modifier.width(10.dp))

            if (index == 3) {
                MoreItemsText()
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Composable
private fun MoreItemsText() {
    Column(modifier = Modifier.width(100.dp).height(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "and")
        Text(text = "more")
        Text(text = "items...")
    }
}

