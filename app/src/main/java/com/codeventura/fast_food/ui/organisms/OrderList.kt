package com.codeventura.fast_food.ui.organisms

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.OrderItem
import com.codeventura.fast_food.ui.moleculs.FoodItem

@Composable
fun OrderList(navController: NavController, orderItems: List<OrderItem>, onDelete: (OrderItem) -> Unit) {
	val itemCount = orderItems.size
	var showDialog by remember { mutableStateOf(false) }
	var selectedOrderItem by remember { mutableStateOf<OrderItem?>(null) }
	
	Column {
		Text(text = "Número de itens encontrados: $itemCount", style = MaterialTheme.typography.bodyMedium)
		LazyColumn {
			items(orderItems) { orderItem ->
				FoodItem(
					foodModel = orderItem.food,
					navController = navController,
					quantity = orderItem.quantity,
					onClick = {
						selectedOrderItem = orderItem
						showDialog = true
						return@FoodItem null
					},
					modifier = Modifier.padding(vertical = 8.dp)
				)
			}
		}
	}
	
	if (showDialog && selectedOrderItem != null) {
		AlertDialog(
			onDismissRequest = { showDialog = false },
			title = {
				Text(text = "Confirmação")
			},
			text = {
				Text(text = "Deseja realmente deletar o item selecionado?")
			},
			confirmButton = {
				Button(
					onClick = {
						selectedOrderItem?.let { onDelete(it) }
						showDialog = false
					}
				) {
					Text("Confirmar")
				}
			},
			dismissButton = {
				Button(
					onClick = { showDialog = false }
				) {
					Text("Cancelar")
				}
			}
		)
	}
}
