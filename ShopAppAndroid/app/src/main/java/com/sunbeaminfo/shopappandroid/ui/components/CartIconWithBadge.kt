package com.sunbeaminfo.shopappandroid.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun CartIconWithBadge(
    count: Int,
    onClick: () -> Unit
) {
    BadgedBox(
        badge = {
            if (count > 0) {
                Badge { Text(count.toString()) }
            }
        }
    ) {
        IconButton(onClick = onClick) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
    }
}
