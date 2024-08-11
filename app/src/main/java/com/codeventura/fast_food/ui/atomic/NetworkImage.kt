package com.codeventura.fast_food.ui.atomic

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(url: String, modifier: Modifier = Modifier) {
	Image(
		painter = rememberAsyncImagePainter(model = url),
		contentDescription = null,
		modifier = modifier,
		contentScale = ContentScale.Crop
	)
}