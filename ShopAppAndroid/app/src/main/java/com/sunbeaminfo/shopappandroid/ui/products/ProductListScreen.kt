package com.sunbeaminfo.shopappandroid.ui.products

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sunbeaminfo.shopappandroid.data.model.Product
import com.sunbeaminfo.shopappandroid.viewmodel.CartViewModel
import com.sunbeaminfo.shopappandroid.ui.components.ShimmerBox

@Composable
fun ProductListScreen(
    category: String,
    cartVM: CartViewModel,
    onBack: () -> Unit,
    onGoToCart: () -> Unit
) {
    BackHandler { onBack() }

    var isLoading by remember { mutableStateOf(true) }

    val products = remember(category) {
        getProductsByCategory(category)
    }

    // Fake loading for premium feel
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(800)
        isLoading = false
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔝 TOP BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = onBack) { Text("← Back") }
            Button(onClick = onGoToCart) { Text("Cart") }
        }

        // 🔄 SHIMMER WHILE LOADING
        if (isLoading) {
            Column(modifier = Modifier.padding(12.dp)) {
                repeat(4) {
                    ShimmerBox()
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        } else {

            // ✅ AMAZON-LIKE GRID
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products) { product ->
                    ProductCard(product) { qty ->
                        cartVM.addToCart(product, qty)
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: Product,
    onAddToCart: (Int) -> Unit
) {
    var qty by remember { mutableStateOf(1) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {

            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                "₹${product.price}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { if (qty > 1) qty-- }) { Text("-") }
                Text(qty.toString(), modifier = Modifier.padding(top = 8.dp))
                Button(onClick = { qty++ }) { Text("+") }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Button(
                onClick = { onAddToCart(qty) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add to Cart")
            }
        }
    }
}
