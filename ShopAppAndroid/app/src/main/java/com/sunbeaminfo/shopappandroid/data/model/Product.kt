package com.sunbeaminfo.shopappandroid.data.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    @DrawableRes val imageRes: Int
)
