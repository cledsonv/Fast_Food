package com.codeventura.fast_food

import android.app.Application
import androidx.room.Room
import com.codeventura.fast_food.data.database.AppDatabase
import com.codeventura.fast_food.data.dao.OrderItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	
	@Provides
	@Singleton
	fun provideDatabase(app: Application): AppDatabase {
		return Room.databaseBuilder(app, AppDatabase::class.java, "app-database").build()
	}
	
	@Provides
	@Singleton
	fun provideOrderItemDao(db: AppDatabase): OrderItemDao {
		return db.orderItemDao()
	}
}