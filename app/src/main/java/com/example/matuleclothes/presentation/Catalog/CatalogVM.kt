package com.example.matuleclothes.presentation.Catalog

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.model.Products
import com.example.network.domain.usecase.GetProductByIdUseCase
import com.example.network.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(CatalogState())
    val state: State<CatalogState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val productsList = getProductsUseCase.invoke(
                    filterField = "id",
                    filterValue = ""
                )
                getCatalog(filterField = "type", filterValue = "Популярные")
                _state.value = state.value.copy(
                    catalog = productsList.items.map {
                        Products(
                            id = it.id,
                            name = it.title,
                            price = it.price,
                            type = it.type,
                            inCart = false)
                    }
                )
            } catch (ex: Exception){
                Log.e("server", ex.message.toString())
            }
        }
    }

    fun onEvent(event: CatalogEvent){
        when(event){
            is CatalogEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value
                )
            }
            CatalogEvent.FirstButtonClick -> {
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
            CatalogEvent.SecondButtonClick -> {
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
            CatalogEvent.ThirdButtonClick -> {
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
    }
}