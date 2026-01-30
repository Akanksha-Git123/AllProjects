package com.sunbeaminfo.shopappandroid.data.remote.dto

// Adjust fields to match your backend's expected JSON keys
data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String? = null,    // optional
    val userType: String? = null        // optional (e.g. "OWNER" / "TENANT")
)
