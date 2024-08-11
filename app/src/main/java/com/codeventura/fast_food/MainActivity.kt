package com.codeventura.fast_food

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.codeventura.fast_food.ui.theme.Fast_FoodTheme
import com.codeventura.fast_food.data.routes.AppNavigation
import com.codeventura.fast_food.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	private val foodViewModel: FoodViewModel by viewModels()
	
	@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
	override fun onCreate(savedInstanceState: Bundle? ) {

		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Fast_FoodTheme {
				AppNavigation(
					foodViewModel
				)
			}
		}
	}
}



