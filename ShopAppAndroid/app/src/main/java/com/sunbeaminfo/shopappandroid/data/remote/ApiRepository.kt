package com.sunbeaminfo.shopappandroid.data.remote

import com.sunbeaminfo.shopappandroid.data.remote.dto.AuthResponse
import com.sunbeaminfo.shopappandroid.data.remote.dto.LoginRequest
import retrofit2.Response

class ApiRepository(
    private val api: ApiService = RetrofitClient.api
) {

    suspend fun login(email: String, password: String): Result<AuthResponse> {
        return try {
            val response: Response<AuthResponse> =
                api.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception("Error code: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
