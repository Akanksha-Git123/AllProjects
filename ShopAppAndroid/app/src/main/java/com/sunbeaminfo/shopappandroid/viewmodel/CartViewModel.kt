package com.sunbeaminfo.shopappandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunbeaminfo.shopappandroid.data.model.CartItem
import com.sunbeaminfo.shopappandroid.data.remote.RetrofitClient
import com.sunbeaminfo.shopappandroid.data.remote.dto.OrderRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    fun addToCart(product: com.sunbeaminfo.shopappandroid.data.model.Product, qty: Int) {
        val list = _cartItems.value.toMutableList()
        val existing = list.find { it.product.id == product.id }

        if (existing != null) {
            existing.quantity += qty
        } else {
            list.add(CartItem(product, qty))
        }
        _cartItems.value = list
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun totalAmount(): Double =
        _cartItems.value.sumOf { it.product.price * it.quantity }

    // ✅ PLACE ORDER TO BACKEND
    fun placeOrder(userId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val request = OrderRequest(
                    userId = userId,
                    items = _cartItems.value.map {
                        OrderRequest.Item(
                            productId = it.product.id,
                            quantity = it.quantity
                        )
                    }
                )

                RetrofitClient.api.placeOrder(request)
                clearCart()
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Order failed")
            }
        }
    }
}
