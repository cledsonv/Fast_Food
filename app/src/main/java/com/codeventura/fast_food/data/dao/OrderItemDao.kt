package com.codeventura.fast_food.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codeventura.fast_food.data.models.OrderItem

@Dao
interface OrderItemDao {
	@Insert
	suspend fun insert(oderItem: OrderItem)
	
	@Query("SELECT * FROM order_items")
	 fun getAll(): List<OrderItem>
	
	@Query("DELETE FROM order_items WHERE id = :id")
	suspend fun deleteById(id: Int)
}