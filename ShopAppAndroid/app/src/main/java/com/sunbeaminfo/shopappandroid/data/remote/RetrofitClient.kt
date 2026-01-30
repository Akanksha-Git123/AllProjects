package com.sunbeaminfo.shopappandroid.data.remote

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    /**
     * Emulator → 10.0.2.2
     * Real phone → use your laptop WiFi IPv4 address
     * Example: http://192.168.1.5:8080/
     */
    private fun getBaseUrl(): String {
        return if (
            Build.FINGERPRINT.contains("generic")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
        ) {
            // Emulator
            "http://10.0.2.2:8080/"
        } else {
            // 🔴 CHANGE THIS IP TO YOUR LAPTOP WIFI IPV4
            "http://192.168.0.185:8080/"
        }
    }

    // Logging interceptor
    private val loggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    // Header interceptor
    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Accept", "application/json")
            .build()
        chain.proceed(request)
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
