package com.codeventura.fast_food.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.ui.moleculs.FoodItem
import com.codeventura.fast_food.viewmodel.FoodViewModel

@Composable
fun FoodList(foodViewModel: FoodViewModel, navController: NavController, route: String) {
	val foodItems by foodViewModel.filteredFoodItems.observeAsState(emptyList())
	val itemCount = foodItems.size
	
	Column  {
		Text(text = "Número de itens encontrados: $itemCount", style = MaterialTheme.typography.bodyMedium)
		LazyColumn(
		
		)  {
			items(foodItems) { foodItem ->
				FoodItem(
					foodModel = foodItem,
					navController = navController,
					onClick = 	{navController.navigate("select-food/$route/${foodItem.id}")},
					modifier = Modifier.padding(vertical = 8.dp)
				)
			}
		}
	}
}
