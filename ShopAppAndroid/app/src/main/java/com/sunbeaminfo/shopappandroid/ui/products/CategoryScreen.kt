package com.sunbeaminfo.shopappandroid.ui.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunbeaminfo.shopappandroid.data.local.CategoryData

@Composable
fun CategoryScreen(onCategoryClick: (String) -> Unit) {

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ✅ USE CategoryData (single source of truth)
        CategoryData.categories.forEach { category ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        onCategoryClick(category.name)
                    }
            ) {
                Text(
                    text = category.name,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
