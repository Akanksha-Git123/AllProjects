package com.sunbeaminfo.shopappandroid.data.local

import com.sunbeaminfo.shopappandroid.data.model.Category

object CategoryData {

    val categories = listOf(
        Category(1, "Grocery"),
        Category(2, "Toilet & Grooming"),   // ✅ FIXED
        Category(3, "Bathing & Grooming"),
        Category(4, "Cold Drinks"),
        Category(5, "Namkeen"),
        Category(6, "Oils"),
        Category(7, "Healthy Stuff"),       // ✅ FIXED (capital S)
        Category(8, "Biscuits")
    )
}
