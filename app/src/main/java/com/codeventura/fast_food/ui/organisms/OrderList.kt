package com.codeventura.fast_food.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.OrderItem
import com.codeventura.fast_food.ui.moleculs.FoodItem

@Composable
fun OrderList(navController: NavController, orderItems: List<OrderItem>) {
	
	val itemCount = orderItems.size
	
	Column {
		Text(text = "Número de itens encontrados: $itemCount", style = MaterialTheme.typography.bodyMedium)
		LazyColumn {
			items(orderItems) { orderItem ->
				FoodItem(
					foodModel = orderItem.food,
					navController = navController,
					quantity = orderItem.quantity,
					modifier = Modifier.padding(vertical = 8.dp)
				)
			}
		}
	}
}
