package com.sunbeaminfo.shopappandroid.repository

import com.sunbeaminfo.shopappandroid.data.model.Product
import com.sunbeaminfo.shopappandroid.data.remote.RetrofitClient

class ProductRepository {

    private val api = RetrofitClient.api

    suspend fun getProducts(): List<Product> {
        val response = api.getProducts()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to load products")
        }
    }
}
