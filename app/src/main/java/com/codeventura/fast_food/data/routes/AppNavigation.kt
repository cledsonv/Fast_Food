package com.codeventura.fast_food.data.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codeventura.fast_food.ui.pages.CategoryPage
import com.codeventura.fast_food.ui.pages.FinishOrderPage
import com.codeventura.fast_food.ui.pages.OrderPage
import com.codeventura.fast_food.ui.pages.FoodsPage
import com.codeventura.fast_food.ui.pages.OrderAllPage
import com.codeventura.fast_food.viewmodel.FoodViewModel

@Composable
fun AppNavigation(foodViewModel: FoodViewModel) {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = "foods") {
		composable("foods") { FoodsPage(navController, foodViewModel) }
		composable(
			"select-food/{route}/{id}",
			arguments = listOf(
				navArgument("route") { type = NavType.StringType },
				navArgument("id") { type = NavType.StringType }
			)
		) { backStackEntry ->
			val route = backStackEntry.arguments?.getString("route")
			val id = backStackEntry.arguments?.getString("id")
			OrderPage(navController =  navController,foodViewModel= foodViewModel)
		}
		composable("finish-order") { FinishOrderPage(navController, foodViewModel) }
		composable("order-all") { OrderAllPage(navController, foodViewModel) }
		composable("category/{route}/{name}"
		, arguments = listOf(
			navArgument("route") { type = NavType.StringType },
			navArgument("name") { type = NavType.StringType },
			)
		)  { backStackEntry ->
			val route = backStackEntry.arguments?.getString("route")
			val name = backStackEntry.arguments?.getString("name")
			if (route != null) {
				if (name != null) {
					CategoryPage(navController =  navController,
						route = route,  name =name ,
						foodViewModel = foodViewModel)
				}
			}
		}
	}
}