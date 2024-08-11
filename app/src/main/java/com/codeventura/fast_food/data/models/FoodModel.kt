package com.codeventura.fast_food.data.models

data class FoodModel(
	val id: String,
	val name: String,
	val dsc: String,
	val price: Double,
	val img: String,
	val rate: Double?,
	val country: String?
)
