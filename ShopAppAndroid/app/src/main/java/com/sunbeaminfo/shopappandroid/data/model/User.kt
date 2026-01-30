package com.sunbeaminfo.shopappandroid.data.model

data class User(
    val id: Long? = null,     // Spring will send back ID if you return saved user
    val name: String,
    val email: String,
    val password: String,
    val role: String
)
