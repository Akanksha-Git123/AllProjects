package com.sunbeaminfo.shopappandroid.ui.products

import com.sunbeaminfo.shopappandroid.R
import com.sunbeaminfo.shopappandroid.data.model.Product

fun getProductsByCategory(category: String): List<Product> {
    return when (category) {

        // 🛒 GROCERY
        "Grocery" -> listOf(
            Product(1, "Toor Daal", "1kg pack", 180.0, R.drawable.daal_toor),
            Product(2, "Moong Daal", "500g pack", 120.0, R.drawable.daal_moong),
            Product(3, "Besan", "1kg pack", 95.0, R.drawable.basan),
            Product(4, "Chana Dal", "1kg pack", 110.0, R.drawable.chana),
            Product(5, "Poha", "1kg pack", 60.0, R.drawable.poha)
        )

        // 🛢️ OILS
        "Oils" -> listOf(
            Product(6, "Gemini Refined Oil", "1L pack", 165.0, R.drawable.gemimirefined),
            Product(7, "Fortune Sunlite Oil", "1L pack", 170.0, R.drawable.fortunesun)
        )

        // 🥤 COLD DRINKS
        "Cold Drinks" -> listOf(
            Product(8, "Coca Cola", "1L Bottle", 65.0, R.drawable.coke),
            Product(9, "Sprite", "750ml Bottle", 55.0, R.drawable.sprite),
            Product(10, "Fanta", "750ml Bottle", 55.0, R.drawable.fanta),
            Product(11, "Pepsi", "1L Bottle", 65.0, R.drawable.pepsi)
        )

        // 🍿 NAMKEEN
        "Namkeen" -> listOf(
            Product(12, "Lays Classic", "52g pack", 20.0, R.drawable.lays_classic),
            Product(13, "Lays Magic Masala", "52g pack", 20.0, R.drawable.laysmagic),
            Product(14, "Aloo Bhujia", "400g pack", 110.0, R.drawable.aloobhujiya),
            Product(15, "Bikaji Bhujia", "400g pack", 120.0, R.drawable.bikajibhujiya),
            Product(16, "Chilli Chatka", "Spicy snack", 35.0, R.drawable.chillichatka),
            Product(17, "Puffcorn", "60g pack", 25.0, R.drawable.puffcorn)
        )

        // 🧼 TOILET & GROOMING
        "Toilet & Grooming" -> listOf(
            Product(18, "Harpic", "500ml", 95.0, R.drawable.harpic),
            Product(19, "Tissue Paper", "Pack of 6", 120.0, R.drawable.tissuepaper),
            Product(20, "Room Freshener", "Lavender", 150.0, R.drawable.roomfresher),
            Product(21, "Odonil", "Bathroom Freshener", 85.0, R.drawable.odonil)
        )

        // 🛁 BATHING & GROOMING (SOAPS & SHAMPOO)
        "Bathing & Grooming" -> listOf(
            Product(22, "Dove Soap", "100g", 55.0, R.drawable.dove),
            Product(23, "Pears Soap", "125g", 60.0, R.drawable.pears),
            Product(24, "Lux Soap", "120g", 40.0, R.drawable.lux),
            Product(25, "Clinic Plus Shampoo", "180ml", 95.0, R.drawable.clinicplus),
            Product(26, "Head & Shoulders", "180ml", 135.0, R.drawable.headshoulder)
        )

        // 💪 HEALTHY STUFF
        "Healthy Stuff" -> listOf(
            Product(27, "Oats", "1kg pack", 180.0, R.drawable.oats),
            Product(28, "Green Tea", "100g", 160.0, R.drawable.greentea),
            Product(29, "Chia Seeds", "250g", 140.0, R.drawable.chiyaseeds)
        )

        // 🍪 BISCUITS
        "Biscuits" -> listOf(
            Product(30, "Parle-G", "800g", 90.0, R.drawable.parle),
            Product(31, "20-20 Biscuits", "450g", 75.0, R.drawable.twenty),
            Product(32, "Hide & Seek", "300g", 95.0, R.drawable.hideandseek),
            Product(33, "Happy Happy", "250g", 40.0, R.drawable.happyhappy)
        )

        else -> emptyList()
    }
}
