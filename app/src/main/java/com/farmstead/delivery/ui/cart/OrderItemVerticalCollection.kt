package com.farmstead.delivery.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farmstead.delivery.domain.OrderItem
import com.farmstead.delivery.domain.formatted
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun OrderItemVerticalCollection(
    orderItems: List<OrderItem>,
    onItemClick: (OrderItem) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 8.dp)) {
        items(
            items = orderItems,
            itemContent = { orderItem ->
                VerticalListItem(orderItem = orderItem, onItemClick = onItemClick)
                Spacer(modifier = Modifier.height(8.dp))
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    orderItem: OrderItem,
    onItemClick: (OrderItem) -> Unit
) {
    val typography = MaterialTheme.typography
    Row {
        GlideImage(imageModel = orderItem.item.image.url, modifier = Modifier
            .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            .width(125.dp)
            .height(125.dp))

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = orderItem.item.name,
                    style = typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 64.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Quantity: ${orderItem.quantity}",
                    style = typography.subtitle2,
                )
            }

            Text(
                text = orderItem.getItemFormattedPrice(),
                style = typography.subtitle2,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

private fun OrderItem.getItemFormattedPrice() =
    this.item.hubItems.first { it.hubID == 2L }.price.formatted()
