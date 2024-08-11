package com.codeventura.fast_food.ui.moleculs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codeventura.fast_food.data.models.FoodRoute
import com.codeventura.fast_food.ui.atomic.NetworkImage

@Composable
fun CategoryWidget(
	foodRoute: FoodRoute,
	navController: NavController
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.width(100.dp)
			.clickable { navController.navigate("category/${foodRoute.route}/${foodRoute.name}") }
	) {
		NetworkImage(
			url = foodRoute.image,
			modifier = Modifier
				.size(80.dp)
				.clip(CircleShape)
		)
		Spacer(modifier = Modifier.height(8.dp))
		Text(
			text = foodRoute.name,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.align(Alignment.CenterHorizontally)
		)
	}
}