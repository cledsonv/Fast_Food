package com.codeventura.fast_food.ui.pages

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.ui.organisms.OrderList
import com.codeventura.fast_food.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OrderAllPage(navController: NavController, foodViewModel: FoodViewModel) {
	
	foodViewModel.getOrders()
	val orderItems by foodViewModel.filteredOrderItems.observeAsState(emptyList())
	
	val searchQuery = remember { mutableStateOf("") }
	
	Scaffold(topBar = {
		TopAppBar(title = { Text("Todos os pedidos") }, navigationIcon = {
			IconButton(onClick = { navController.navigateUp() }) {
				Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
			}
		}
		)
	})
	{
		Column(
			modifier = Modifier
				.padding(it)
				.padding(horizontal = 16.dp, vertical = 10.dp)
		) {
			TextField(
				value = searchQuery.value,
				onValueChange = { query ->
					searchQuery.value = query
					foodViewModel.filterOrderItems(query)
				},
				label = { Text("Pesquisar") },
				modifier = Modifier.fillMaxWidth()
			)
			Spacer(modifier = Modifier.padding(10.dp))
			OrderList(navController = navController, orderItems = orderItems,
				onDelete = { orderItem ->
					orderItem.getId()?.let { it1 -> foodViewModel.deleteOrder(it1) }
				}
			)
			
		}
		
	}
}