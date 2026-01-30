package com.sunbeaminfo.shopappandroid.ui.products

import androidx.compose.runtime.*
import com.sunbeaminfo.shopappandroid.viewmodel.CartViewModel
import com.sunbeaminfo.shopappandroid.ui.cart.CartScreen

@Composable
fun ProductsScreen(
    cartVM: CartViewModel
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var showCart by remember { mutableStateOf(false) }

    when {

        // 🛒 CART SCREEN
        showCart -> {
            CartScreen(
                cartVM = cartVM,
                userId = 5L, // TEMP userId
                onBack = { showCart = false },
                onViewOrders = {
                    // For now just close cart
                    // (Order history navigation handled in MainActivity)
                    showCart = false
                }
            )
        }

        // 📂 CATEGORY LIST
        selectedCategory == null -> {
            CategoryScreen { category ->
                selectedCategory = category
            }
        }

        // 📦 PRODUCT LIST
        else -> {
            ProductListScreen(
                category = selectedCategory!!,
                cartVM = cartVM,
                onBack = { selectedCategory = null },
                onGoToCart = { showCart = true }
            )
        }
    }
}
