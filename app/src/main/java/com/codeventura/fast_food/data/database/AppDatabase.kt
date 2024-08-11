package com.codeventura.fast_food.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codeventura.fast_food.data.dao.OrderItemDao
import com.codeventura.fast_food.data.mappers.Mappers
import com.codeventura.fast_food.data.models.OrderItem

@Database(entities = [OrderItem::class], version = 1)
@TypeConverters(Mappers::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun orderItemDao(): OrderItemDao
}