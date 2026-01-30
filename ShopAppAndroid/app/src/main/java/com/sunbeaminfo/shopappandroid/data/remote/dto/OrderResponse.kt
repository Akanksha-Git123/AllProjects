package com.sunbeaminfo.shopappandroid.data.remote.dto

data class OrderResponse(
    val id: Long,
    val totalAmount: Double,
    val status: String,
    val createdAt: String,
    val items: List<Item>
) {
    data class Item(
        val productName: String,
        val quantity: Int,
        val price: Double
    )
}
