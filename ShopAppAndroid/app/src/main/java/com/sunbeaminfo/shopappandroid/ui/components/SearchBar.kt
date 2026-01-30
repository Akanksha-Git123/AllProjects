package com.sunbeaminfo.shopappandroid.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun SearchBar(
    hint: String = "Search products",
    onSearch: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
            onSearch(it)
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(hint) },
        singleLine = true
    )
}
