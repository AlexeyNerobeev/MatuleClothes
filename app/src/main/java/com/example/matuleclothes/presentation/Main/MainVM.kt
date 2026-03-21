package com.example.matuleclothes.presentation.Main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.model.Products
import com.example.matuleclothes.domain.usecase.LoadCachedProductsUseCase
import com.example.matuleclothes.domain.usecase.LoadFilterCachedProductsUseCase
import com.example.matuleclothes.domain.usecase.SaveProductsUseCase
import com.example.matuleclothes.domain.usecase.SendNotificationUseCase
import com.example.network.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val sendNotificationUseCase: SendNotificationUseCase,
    private val saveProductsUseCase: SaveProductsUseCase,
    private val loadCachedProductsUseCase: LoadCachedProductsUseCase,
    private val loadFilterCachedProductsUseCase: LoadFilterCachedProductsUseCase
): ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        sendNotificationUseCase.invoke("main", "main")
        viewModelScope.launch(Dispatchers.IO) {
            loadProducts()
            try {
                val productsList = getProductsUseCase.invoke(
                    filterField = "id",
                    filterValue = ""
                )
                getCatalog(filterField = "type", filterValue = "Популярные")
                _state.value = state.value.copy(
                    productsList = productsList.items.map {
                        Products(name = it.title, price = it.price)
                    }
                )
                saveProducts()
            } catch (ex: Exception){
                Log.e("server", ex.message.toString())
            }
        }
    }

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value
                )
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val newCatalog = state.value.catalog.filter {
                            it.name.contains(state.value.search.trim(), ignoreCase = true)
                        }
                        _state.value = state.value.copy(
                            catalog = newCatalog
                        )
//                        val products = getProductsUseCase.invoke(
//                            filterField = "title",
//                            filterValue = state.value.search
//                        )
//                        _state.value = state.value.copy(
//                            catalog = products.items.map {
//                                Products(id = it.id,
//                                    name = it.title,
//                                    price = it.price,
//                                    type = it.type,
//                                    inCart = false)
//                            }
//                        )
                    } catch (ex: Exception){
                        Log.e("search", ex.message.toString())
                    }
                }
            }
            MainEvent.FirstButtonClick -> {
                _state.value = state.value.copy(
                    firstActive = !state.value.firstActive
                )
                if(state.value.firstActive){
                    _state.value = state.value.copy(
                        secondActive = false,
                        thirdActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Популярные")
                        } catch(ex: Exception){
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }
            MainEvent.SecondButtonClick -> {
                _state.value = state.value.copy(
                    secondActive = !state.value.secondActive
                )
                if(state.value.secondActive){
                    _state.value = state.value.copy(
                        firstActive = false,
                        thirdActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Женщинам")
                        } catch(ex: Exception){
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }
            MainEvent.ThirdButtonClick -> {
                _state.value = state.value.copy(
                    thirdActive = !state.value.thirdActive
                )
                if(state.value.thirdActive){
                    _state.value = state.value.copy(
                        firstActive = false,
                        secondActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Мужчинам")
                        } catch(ex: Exception){
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }
        }
    }

    private suspend fun getCatalog(filterField: String, filterValue: String){
        try {
            val catalog = getProductsUseCase.invoke(filterField, filterValue)
            _state.value = state.value.copy(
                catalog = catalog.items.map {
                    Products(name = it.title,
                        type = it.type,
                        price = it.price,
                        id = it.id,
                        inCart = false)
                }
            )
        } catch (e: Exception) {
            try {
                val newCatalog = loadFilterCachedProductsUseCase.invoke(filterValue)
                _state.value = state.value.copy(
                    catalog = newCatalog
                )
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private suspend fun saveProducts(){
        try {
            val products = getProductsUseCase.invoke("id", "").items.map {
                Products(
                    id = it.id,
                    name = it.title,
                    price = it.price,
                    type = it.type,
                    inCart = false
                )
            }
            saveProductsUseCase.invoke(products)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun loadProducts(){
        try {
            val products = loadCachedProductsUseCase.invoke()
            _state.value = state.value.copy(
                catalog = products,
                productsList = products
            )
            Log.i("products", products.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}