package com.sunbeaminfo.shopappandroid.ui.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunbeaminfo.shopappandroid.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartVM: CartViewModel,
    userId: Long,
    onBack: () -> Unit,
    onViewOrders: () -> Unit
) {
    val items by cartVM.cartItems.collectAsState()
    var showSuccess by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        TextButton(onClick = onBack) { Text("← Back") }

        Text(
            "My Cart",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (items.isEmpty()) {
            Text("Your cart is empty")
        } else {
            items.forEach {
                Text("${it.product.name} × ${it.quantity}")
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text("Total: ₹${cartVM.totalAmount()}")

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    cartVM.placeOrder(
                        userId = userId,
                        onSuccess = { showSuccess = true },
                        onError = {}
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Place Order")
            }
        }
    }

    // ✅ PREMIUM SUCCESS ANIMATION
    AnimatedVisibility(
        visible = showSuccess,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                Button(onClick = {
                    showSuccess = false
                    onViewOrders()
                }) {
                    Text("View Orders")
                }
            },
            title = { Text("Order Successful 🎉") },
            text = { Text("Delivery in 30–45 minutes") }
        )
    }
}
