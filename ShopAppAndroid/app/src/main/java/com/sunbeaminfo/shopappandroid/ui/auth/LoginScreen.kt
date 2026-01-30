package com.sunbeaminfo.shopappandroid.ui.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunbeaminfo.shopappandroid.data.remote.dto.LoginRequest
import com.sunbeaminfo.shopappandroid.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNeedRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", fontSize = 28.sp, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@Button
            }

            scope.launch {
                loading = true
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitClient.api.login(LoginRequest(email.trim(), password))
                    }
                    loading = false

                    if (response.isSuccessful) {
                        val body = response.body()
                        // Save token if present (assumes AuthResponse has `token` field)
                        val token = try { body?.token } catch (e: Exception) { null }

                        if (!token.isNullOrBlank()) {
                            saveTokenToPrefs(context, token)
                        }

                        Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                        onLoginSuccess()
                    } else {
                        Toast.makeText(context, "Login failed: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                } catch (t: Throwable) {
                    loading = false
                    Toast.makeText(context, "Network error: ${t.message ?: "Unknown"}", Toast.LENGTH_SHORT).show()
                }
            }
        }, modifier = Modifier.fillMaxWidth(), enabled = !loading) {
            Text(if (loading) "Logging in..." else "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onNeedRegister, modifier = Modifier.fillMaxWidth()) {
            Text("Create account")
        }
    }
}

// Simple SharedPreferences helper to persist token
private fun saveTokenToPrefs(context: Context, token: String) {
    val pref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    pref.edit().putString("auth_token", token).apply()
}
