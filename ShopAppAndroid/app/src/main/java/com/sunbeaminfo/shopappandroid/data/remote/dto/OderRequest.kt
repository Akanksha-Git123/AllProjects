package com.sunbeaminfo.shopappandroid.data.remote.dto

data class OrderRequest(
    val userId: Long,
    val items: List<Item>
) {
    data class Item(
        val productId: Long,
        val quantity: Int
    )
}
