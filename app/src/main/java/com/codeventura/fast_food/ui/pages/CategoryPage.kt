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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
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
import com.codeventura.fast_food.ui.organisms.FoodList
import com.codeventura.fast_food.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryPage(
	navController: NavController,
	foodViewModel: FoodViewModel,
	route: String,
	name: String
) {
	foodViewModel.fetchFoodItems(route = route)
	val searchQuery = remember { mutableStateOf("") }
	val errorMessage = foodViewModel.errorMessage.value
	
	Scaffold(modifier = Modifier.fillMaxSize(),
		topBar = {
			TopAppBar(
				title = { Text(name) },navigationIcon = {
					IconButton(onClick = { navController.navigateUp() }) {
						Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
					}
				}
			)
		}) {
		Column(
			modifier = Modifier
				.padding(it)
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 10.dp)
		) {
			if ( errorMessage != null) {
				Text(
					text = errorMessage,
					color = MaterialTheme.colorScheme.error,
					modifier = Modifier.padding(16.dp)
				)
				Button(onClick = { foodViewModel.clearErrorMessage(route = foodViewModel.foodRoutes.last().route) }) {
					Text("Tentar novamente")
				}
			} else {
			TextField(
				value = searchQuery.value,
				onValueChange = { query ->
					searchQuery.value = query
					foodViewModel.filterFoodItems(query)
				},
				label = { Text("Pesquisar") },
				modifier = Modifier.fillMaxWidth()
			)
			FoodList(
				foodViewModel,
				navController = navController,
				route = route
			)
		}
	}
}}