package com.codeventura.fast_food.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.OrderItem
import com.codeventura.fast_food.ui.moleculs.FoodItem
import com.codeventura.fast_food.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FinishOrderPage(navController: NavController, foodViewModel: FoodViewModel) {
	val order by foodViewModel.order.observeAsState()
	Scaffold(
		topBar = {
			TopAppBar(title = { Text("Pedido Confirmado") }, navigationIcon = {
				IconButton(onClick = { navController.navigateUp() }) {
					Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
				}
			}
			)
		},
		bottomBar = {
			BottomAppBar(
				containerColor = Color.Transparent
			) {
				Button(
					onClick = {
						order?.let { foodViewModel.saveOrderRoom(orderItem = it) }
						navController.navigate("foods")
					},
					modifier = Modifier
						.fillMaxWidth()
						.padding(16.dp)
						.height(50.dp)
				) {
					Text("Finalizar")
				}
			}
		}
	) {
		order?.let { order ->
			Column(
				modifier = Modifier
					.padding(it)
					.fillMaxSize()
					.padding(16.dp)
			) {
				
				FoodItem(
					foodModel = order.food,
					navController = navController,
					quantity = order.quantity
				)
			}
		}
	}
}