package com.codeventura.fast_food.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.ui.atomic.NetworkImage
import com.codeventura.fast_food.ui.moleculs.CategoryWidget
import com.codeventura.fast_food.ui.organisms.FoodList
import com.codeventura.fast_food.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FoodsPage(navController: NavController, foodViewModel: FoodViewModel) {
	foodViewModel.fetchFoodItems(route = foodViewModel.foodRoutes.last().route)
	val searchQuery = remember { mutableStateOf("") }
	
	
	Scaffold(modifier = Modifier.fillMaxSize(),
		topBar = {
			TopAppBar(title = { Text("Faça o seu pedido!!!") },
				actions = {
					IconButton(onClick = { navController.navigate("order-all") }) {
						Icon(Icons.Filled.ShoppingCart, contentDescription = "Pedidos")
					}
				}
			
			
			)
		}) {
		LazyColumn(
			modifier = Modifier
				.padding(it)
				.fillMaxSize()
				.padding(horizontal = 16.dp, vertical = 10.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			item{
				TextField(
					value = searchQuery.value,
					onValueChange = { query ->
						searchQuery.value = query
						foodViewModel.filterFoodItems(query)
					},
					label = { Text("Pesquisar") },
					modifier = Modifier.fillMaxWidth()
				)
			}
			item {
				LazyRow(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(16.dp)
				) {
					items(foodViewModel.foodRoutes) { foodRoute ->
						CategoryWidget(foodRoute = foodRoute, navController = navController)
					}
				}
			}
			
	
			
			item {
				FoodList(
					foodViewModel = foodViewModel,
					navController = navController,
					route = foodViewModel.foodRoutes.last().route
				)
			}
		}
	}
}