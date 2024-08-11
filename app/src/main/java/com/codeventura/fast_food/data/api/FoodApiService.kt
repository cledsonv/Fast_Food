package com.codeventura.fast_food.data.api

import com.codeventura.fast_food.data.models.FoodModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApiService {
	@GET("{route}")
	fun getFoodItems(@Path("route") route: String): Call<List<FoodModel>>
	
	@GET("{route}/{id}")
	fun getFoodById(@Path("route") route: String, @Path("id") id: String): Call<FoodModel>
	
}