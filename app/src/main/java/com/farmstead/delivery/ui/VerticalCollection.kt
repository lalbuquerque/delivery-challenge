package com.farmstead.delivery.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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

@Composable
fun VerticalCollection(
    deliveries: List<Delivery>,
    onItemClick: (Delivery) -> Unit
) {
    LazyColumn {
        items(
            items = deliveries,
            itemContent = { delivery ->
                VerticalListItem(delivery = delivery, onItemClick = onItemClick)
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun VerticalListItem(
    delivery: Delivery,
    onItemClick: (Delivery) -> Unit
) {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 4.dp)
            .height(56.dp)
            .clickable(onClick = { onItemClick(delivery) }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
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
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

