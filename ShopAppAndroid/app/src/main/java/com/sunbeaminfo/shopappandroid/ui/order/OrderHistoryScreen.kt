package com.sunbeaminfo.shopappandroid.ui.order

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunbeaminfo.shopappandroid.data.remote.RetrofitClient
import com.sunbeaminfo.shopappandroid.data.remote.dto.OrderResponse

@Composable
fun OrderHistoryScreen(
    userId: Long,
    onBack: () -> Unit
) {
    var orders by remember { mutableStateOf<List<OrderResponse>>(emptyList()) }

    LaunchedEffect(Unit) {
        val response = RetrofitClient.api.getOrderHistory(userId)
        if (response.isSuccessful) {
            orders = response.body() ?: emptyList()
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        TextButton(onClick = onBack) { Text("← Back") }

        Text("My Orders", style = MaterialTheme.typography.titleLarge)

        if (orders.isEmpty()) {
            Text("No orders yet")
        } else {
            LazyColumn {
                items(orders) { order ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Order #${order.id}")
                            Text("Total: ₹${order.totalAmount}")
                            Text("Status: ${order.status}")
                            Text("Delivery in 30–45 minutes")

                            Spacer(modifier = Modifier.height(6.dp))
                            order.items.forEach {
                                Text("• ${it.productName} x ${it.quantity}")
                            }
                        }
                    }
                }
            }
        }
    }
}
