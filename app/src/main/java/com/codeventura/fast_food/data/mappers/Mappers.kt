package com.codeventura.fast_food.data.mappers


import androidx.room.TypeConverter
import com.codeventura.fast_food.data.models.FoodModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Mappers {
	@TypeConverter
	fun fromFoodModel(foodModel: FoodModel): String {
		return Gson().toJson(foodModel)
	}
	
	@TypeConverter
	fun toFoodModel(foodModelString: String): FoodModel {
		val type = object : TypeToken<FoodModel>() {}.type
		return Gson().fromJson(foodModelString, type)
	}
}