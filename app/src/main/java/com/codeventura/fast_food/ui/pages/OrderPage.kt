package com.codeventura.fast_food.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.OrderItem
import com.codeventura.fast_food.ui.atomic.NetworkImage
import com.codeventura.fast_food.ui.atomic.StarRatingBar
import com.codeventura.fast_food.viewmodel.FoodViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "DefaultLocale")
@Composable
fun OrderPage(navController: NavController, foodViewModel: FoodViewModel) {
	val backStackEntry = navController.currentBackStackEntry
	val foodId = backStackEntry?.arguments?.getString("id")
	val route = backStackEntry?.arguments?.getString("route")
	
	val foodItem by foodViewModel.getFoodItemById(id = foodId ?: "", route = route ?: "" ).observeAsState()
	var quantity by remember { mutableIntStateOf(1) }
	
	Scaffold(topBar = {
		TopAppBar(title = { Text("Selecione a quantidade") }, navigationIcon = {
			IconButton(onClick = { navController.navigateUp() }) {
				Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
			}
		}
		)
	},
		bottomBar = {
			BottomAppBar(
				containerColor = Color.Transparent
			)  {
				Button(
					onClick = {
						foodItem?.let { food ->
							foodViewModel.saveOrder(OrderItem(food=  food, quantity =  quantity))
							navController.navigate("finish-order")
						}
					},
					modifier = Modifier.fillMaxWidth().padding(16.dp).height(50.dp)
				) {
					Text("Concluir")
				}
			}}
	) {
		LazyColumn(modifier = Modifier
			.padding(it)
			.padding(horizontal = 16.dp, vertical = 10.dp)) {
			item {
				foodItem?.let { food ->
					NetworkImage(url = food.img, 	modifier = Modifier
						.height(250.dp)
						.fillMaxWidth()
						.clip(RoundedCornerShape(16.dp)))
					Text(text = food.name,  modifier = Modifier
						.padding(top = 20.dp)
						.fillMaxWidth()
					,   textAlign = TextAlign.Center,)
					Text(
						text = "R$ ${food.price}",
						modifier = Modifier
							.fillMaxWidth()
						,
						textAlign = TextAlign.Center,
					)
					Row(modifier = Modifier
						.padding(top = 10.dp)
						.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
						StarRatingBar(
							maxStars = 5,
							rating = food.rate ?: 0.0,
						)
						
					}
					
					Text(text = food.dsc, modifier = Modifier.padding(top = 20.dp))
					
					Spacer(modifier = Modifier.height(20.dp))
				
					Text(text = "Valor Total:")
					Text(text = "R$ ${String.format("%.2f", food.price * quantity)}",)
					Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
						Button(onClick = { if (quantity > 1) quantity-- }) {
							Text("-")
						}
						Spacer(modifier = Modifier.width(16.dp))
						Text(text = "$quantity", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.align(Alignment.CenterVertically))
						Spacer(modifier = Modifier.width(16.dp))
						Button(onClick = { quantity++ }) {
							Text("+")
						}
					}
				}
			}
		}
	}
}