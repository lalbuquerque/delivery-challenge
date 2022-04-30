package com.farmstead.delivery.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChipLabel(text: String, modifier: Modifier) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .then(modifier),
        elevation = 8.dp,
        shape = RoundedCornerShape(4.dp),
        color = MaterialTheme.colors.primary,
    ) {
        Text(text = text,
            style = typography.subtitle2,
            modifier = Modifier.padding(
                start = 4.dp,
                top = 2.dp,
                bottom = 2.dp,
                end = 4.dp
            )
        )
    }
}