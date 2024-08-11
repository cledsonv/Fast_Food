package com.codeventura.fast_food.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
	
	private const val BASE_URL = "https://free-food-menus-api.onrender.com/"
	
	@Provides
	@Singleton
	fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}
	
	@Provides
	@Singleton
	fun provideFoodApiService(retrofit: Retrofit): FoodApiService {
		return retrofit.create(FoodApiService::class.java)
	}
}
