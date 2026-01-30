package com.sunbeaminfo.shopappandroid.data.remote

import com.sunbeaminfo.shopappandroid.data.model.Product
import com.sunbeaminfo.shopappandroid.data.remote.dto.AuthResponse
import com.sunbeaminfo.shopappandroid.data.remote.dto.LoginRequest
import com.sunbeaminfo.shopappandroid.data.remote.dto.RegisterRequest
import com.sunbeaminfo.shopappandroid.data.remote.dto.OrderRequest
import com.sunbeaminfo.shopappandroid.data.remote.dto.OrderResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // 🔐 AUTH -------------------------------------------------

    @POST("/api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>

    @POST("/api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<AuthResponse>


    // 📦 PRODUCTS --------------------------------------------

    @GET("/api/products")
    suspend fun getProducts(): Response<List<Product>>


    // 🛒 ORDERS -----------------------------------------------

    // ✅ Place order
    @POST("/api/orders")
    suspend fun placeOrder(
        @Body request: OrderRequest
    ): Response<Unit>

    @GET("/api/orders/user/{userId}")
    suspend fun getOrderHistory(
        @Path("userId") userId: Long
    ): Response<List<OrderResponse>>

}
