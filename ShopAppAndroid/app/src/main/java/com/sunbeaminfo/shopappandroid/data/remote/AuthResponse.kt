package com.sunbeaminfo.shopappandroid.data.remote.dto

data class AuthResponse(
    val token: String?,
    val userId: Long?,
    val email: String?,
    val fullName: String?
)
