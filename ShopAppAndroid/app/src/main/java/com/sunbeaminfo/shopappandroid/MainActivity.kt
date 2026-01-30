package com.sunbeaminfo.shopappandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.sunbeaminfo.shopappandroid.ui.auth.LoginScreen
import com.sunbeaminfo.shopappandroid.ui.auth.RegisterScreen
import com.sunbeaminfo.shopappandroid.ui.products.CategoryScreen
import com.sunbeaminfo.shopappandroid.ui.products.ProductListScreen
import com.sunbeaminfo.shopappandroid.ui.cart.CartScreen
import com.sunbeaminfo.shopappandroid.ui.order.OrderHistoryScreen
import com.sunbeaminfo.shopappandroid.viewmodel.CartViewModel
import com.sunbeaminfo.shopappandroid.ui.theme.ShopAppAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShopAppAndroidTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F6F8)
                ) {

                    val navController = rememberNavController()
                    val cartVM: CartViewModel = viewModel()

                    // 🔐 TEMP logged-in user (exists in DB)
                    val loggedInUserId = 5L

                    NavHost(
                        navController = navController,
                        startDestination = "register"
                    ) {

                        // 🔐 REGISTER
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    navController.navigate("login") {
                                        popUpTo("register") { inclusive = true }
                                    }
                                },
                                onAlreadyHaveAccount = {
                                    navController.navigate("login")
                                }
                            )
                        }

                        // 🔐 LOGIN
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("categories") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onNeedRegister = {
                                    navController.navigate("register")
                                }
                            )
                        }

                        // 📂 CATEGORIES
                        composable("categories") {
                            CategoryScreen { category ->
                                navController.navigate("products/$category")
                            }
                        }

                        // 📦 PRODUCTS
                        composable(
                            route = "products/{category}",
                            arguments = listOf(
                                navArgument("category") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->

                            val category =
                                backStackEntry.arguments?.getString("category") ?: ""

                            ProductListScreen(
                                category = category,
                                cartVM = cartVM,
                                onBack = { navController.popBackStack() },
                                onGoToCart = { navController.navigate("cart") }
                            )
                        }

                        // 🛒 CART
                        composable("cart") {
                            CartScreen(
                                cartVM = cartVM,
                                userId = loggedInUserId,
                                onBack = { navController.popBackStack() },
                                onViewOrders = {
                                    navController.navigate("orders")
                                }
                            )
                        }

                        // 📜 ORDER HISTORY
                        composable("orders") {
                            OrderHistoryScreen(
                                userId = loggedInUserId,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
