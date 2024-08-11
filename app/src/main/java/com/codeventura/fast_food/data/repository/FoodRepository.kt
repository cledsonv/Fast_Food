package com.codeventura.fast_food.data.repository

import com.codeventura.fast_food.data.api.FoodApiService
import com.codeventura.fast_food.data.models.FoodModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FoodRepository @Inject constructor(
	private val api : FoodApiService
) {
	
	fun getFoodItems(route: String ,onResult: (List<FoodModel>?) -> Unit) {
		api.getFoodItems(route = route).enqueue(object : Callback<List<FoodModel>> {
			override fun onResponse(call: Call<List<FoodModel>>, response: Response<List<FoodModel>>) {
				if (response.isSuccessful) {
					println("API Response: ${response.body()}")
					onResult(response.body())
				} else {
					println("Response not successful: ${response.errorBody()?.string()}")
					onResult(null)
				}
			}
			
			override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
				println("API Call failed: ${t.message}")
				onResult(null)
			}
		})
	}
	fun getFoodItemById(id: String, route: String, onResult: (FoodModel?) -> Unit) {
	api.getFoodById(id =  id, route = route).enqueue(object : Callback<FoodModel> {
			override fun onResponse(call: Call<FoodModel>, response: Response<FoodModel>) {
				if (response.isSuccessful) {
					println("API Response: ${response.body()}")
					onResult(response.body())
				} else {
					println("Response not successful: ${response.errorBody()?.string()}")
					onResult(null)
				}
			}
			
			override fun onFailure(call: Call<FoodModel>, t: Throwable) {
				println("API Call failed: ${t.message}")
				onResult(null)
			}
		})
	}
	
	
}
