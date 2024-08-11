package com.codeventura.fast_food.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.codeventura.fast_food.data.dao.OrderItemDao
import com.codeventura.fast_food.data.models.FoodModel
import com.codeventura.fast_food.data.models.FoodRoute
import com.codeventura.fast_food.data.models.OrderItem
import com.codeventura.fast_food.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
	private val orderItemDao: OrderItemDao,
	private val repository: FoodRepository
) : ViewModel() {
	
	private val _foodItems = MutableLiveData<List<FoodModel>>()
	val foodItems: LiveData<List<FoodModel>> get() = _foodItems
	val order: MutableLiveData<OrderItem> = MutableLiveData()
	val orderItems: MutableLiveData<List<OrderItem>> = MutableLiveData()
	private val _filteredFoodItems = MutableLiveData<List<FoodModel>>()
	val filteredFoodItems: LiveData<List<FoodModel>> get() = _filteredFoodItems
	private val _filteredOrderItems = MutableLiveData<List<OrderItem>>()
	val filteredOrderItems: LiveData<List<OrderItem>> get() = _filteredOrderItems
	
	
	val foodRoutes = listOf(
		FoodRoute(name = "Churrasco", route = "bbqs", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTl0zPT9JnZjHZCKFFHzIPbe0wS7MlwE568EQ&s"),
		FoodRoute(name = "Melhores Alimentos", route = "best-foods", image = "https://wx.mlcdn.com.br/ponzi/production/portaldalu/43344_01.jpg"),
		FoodRoute(name = "Pães", route = "breads", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGBjevCIICr24E66wMO9CWPSHathASA35R8Q&s"),
		FoodRoute(name = "Hambúrgueres", route = "burgers", image = "https://www.sabornamesa.com.br/media/k2/items/cache/bf1e20a4462b71e3cc4cece2a8c96ac8_XL.jpg"),
		FoodRoute(name = "Chocolates", route = "chocolates", image = "https://ecp.yusercontent.com/mail?url=https%3A%2F%2Fcseprs3.s3.amazonaws.com%2Femail-editor-files%2Faee2f952-3231-436d-a46e-08d9f0b2efaa%2F7cb62c07-dd0b-4932-a21e-107ea18283b8.jpg&t=1720194785&ymreqid=3ea9396a-53ac-76b5-1c06-b70008010500&sig=eDz4lnJyH6Htraf2J7zCQA--~D"),
		FoodRoute(name = "Sobremesas", route = "desserts", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQXW098aMgh0oLDONxBtWs2ff1DHYS2gYo6HA&s"),
		FoodRoute(name = "Bebidas", route = "drinks", image = "https://conteudo.imguol.com.br/c/entretenimento/74/2022/09/13/drinques-bebida-alcoolica-tequila-cerveja-chopp-gim-martini-alcool-copos-tacas-1663094165597_v2_4x3.jpg"),
		FoodRoute(name = "Frango Frito", route = "fried-chicken", image = "https://pubimg.band.uol.com.br/files/814f09873d737cbc500d.png"),
		FoodRoute(name = "Sorvetes", route = "ice-cream", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTp98KIc66cgKAPeOwIBnqKb9xeoHIFXf8wMA&s"),
		FoodRoute(name = "Pizzas", route = "pizzas", image = "https://istoe.com.br/wp-content/uploads/2023/07/feche-a-pizza-italiana-sobre-o-queijo-cole-o-foco-seletivo-generativo-ai-1258-153047.jpeg"),
		FoodRoute(name = "Carnes de Porco", route = "porks", image = "https://www.juliatto.com.br/wp-content/uploads/2023/05/preparar-carne-de-porco-de-forma-saudavel.jpg"),
		FoodRoute(name = "Sanduíches", route = "sandwiches", image = "https://www.unochapeco.edu.br/static/data/portal/noticias/fotos/960x960/9879.jpg"),
		FoodRoute(name = "Salsichas", route = "sausages", image = "https://conteudo.imguol.com.br/c/entretenimento/0c/2018/09/18/tipos-de-linguicas-1537301783500_v2_900x506.jpg"),
		FoodRoute(name = "Bifes", route = "steaks", image = "https://www.pingodoce.pt/wp-content/uploads/2016/06/bifes-angus-com-presunto.jpg"),
		FoodRoute(name = "Nossos Alimentos", route = "our-foods", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfQtp6m__wPVhZ4Jv99xneEo4ESOTX9WbZGG0r2dNTqeBdMWFuxdbM7FGiMs-PmnY4QIs&usqp=CAU")
	)
	
	init {
		fetchFoodItems(
			route = foodRoutes.last().route
		)
	}
	
	fun fetchFoodItems(route: String) {
		viewModelScope.launch {
			repository.getFoodItems(route = route) { items ->
				_foodItems.postValue(items ?: emptyList())
				_filteredFoodItems.postValue(items ?: emptyList())
			}
		}
	}
	
	
	fun filterFoodItems(query: String) {
		_filteredFoodItems.value = _foodItems.value?.filter {
			it.name.contains(query, ignoreCase = true)
		}
	}
	
	fun getFoodItemById(id: String, route: String): MutableLiveData<FoodModel?> {
		val foodItem = MutableLiveData<FoodModel?>()
		viewModelScope.launch {
			repository.getFoodItemById(id =id, route= route) { item ->
				foodItem.postValue(item)
			}
		}
		return foodItem
	}
	
	fun saveOrder(orderItem: OrderItem) {
		order.postValue(orderItem)
	}
	
	fun saveOrderRoom(orderItem: OrderItem) {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				orderItemDao.insert(orderItem)
				// Log ou outra forma de feedback para confirmar que a inserção foi realizada
				Log.d("OrderViewModel", "Pedido inserido com sucesso: $orderItem")
			} catch (e: Exception) {
				// Lidar com o erro, por exemplo, registrar ou mostrar uma mensagem de erro
				Log.e("OrderViewModel", "Erro ao inserir pedido", e)
			}
		}
	}
	
	fun getOrders() {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val orders = orderItemDao.getAll()
				orderItems.postValue(orders)
				_filteredOrderItems.postValue(orders)
				Log.d("OrderViewModel", "Pedidos obtidos: $orders")
			} catch (e: Exception) {
				// Lidar com o erro, por exemplo, registrar ou mostrar uma mensagem de erro
				Log.e("OrderViewModel", "Erro ao buscar pedidos", e)
			}
		}
	}
	
	fun filterOrderItems(query: String) {
		_filteredOrderItems.value = orderItems.value?.filter {
			it.food.name.contains(query, ignoreCase = true)
		}
	}

}