package com.codeventura.fast_food.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItem(
	@PrimaryKey(autoGenerate = true)
	private val id : Int? = null,
	val food : FoodModel,
	val quantity : Int
){
	fun getId(): Int? {
		return id
	}
}

