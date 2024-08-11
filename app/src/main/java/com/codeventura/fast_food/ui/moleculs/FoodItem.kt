package com.codeventura.fast_food.ui.moleculs

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codeventura.fast_food.ui.atomic.NetworkImage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.FoodModel
import com.codeventura.fast_food.ui.atomic.StarRatingBar

@SuppressLint("DefaultLocale")
@Composable
fun FoodItem(
	foodModel: FoodModel, modifier: Modifier = Modifier, navController: NavController,
	onClick: (() -> Unit?)? = null, quantity: Int? = null
) {
	Card(modifier = modifier
		.padding(bottom = 10.dp)
		.fillMaxWidth()
		.clickable {
			if (onClick != null) {
				onClick()
			}
		}) {
		Row(modifier = modifier.padding(16.dp)) {
			NetworkImage(
				url = foodModel.img,
				modifier = Modifier
					.width(150.dp).height(150.dp)
					.clip(RoundedCornerShape(16.dp))
			)
			Column(modifier = Modifier.padding(start = 16.dp)) {
				Text(
					text = foodModel.name,
					style = MaterialTheme.typography.titleLarge,
					modifier = Modifier.padding(top = 8.dp)
				)
				Text(
					text = if( quantity == null)   " R$ ${foodModel.price}" else "Total: R$ ${String.format("%.2f", foodModel.price * quantity)}",
					style = MaterialTheme.typography.titleSmall,
					modifier = Modifier.padding(top = 8.dp)
				)
				if (quantity != null) {
					Text(
						text = " Itens Pedido: $quantity",
						style = MaterialTheme.typography.titleSmall,
						modifier = Modifier.padding(bottom = 8.dp)
					)
				}
				StarRatingBar(
					maxStars = 5,
					rating = foodModel.rate ?: 0.0
				)
				Text(
					text = foodModel.dsc,
					style = MaterialTheme.typography.bodyMedium,
					modifier = Modifier.padding(top = 4.dp)
				)
			}
			
		}
	}
	
}