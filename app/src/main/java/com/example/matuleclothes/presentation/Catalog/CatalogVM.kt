package com.example.matuleclothes.presentation.Catalog

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.model.Products
import com.example.network.domain.usecase.CreateCartUseCase
import com.example.network.domain.usecase.GetProductByIdUseCase
import com.example.network.domain.usecase.GetProductsUseCase
import com.example.network.domain.usecase.LoadBucketIdUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import com.example.network.domain.usecase.SaveBucketIdUseCase
import com.example.network.domain.usecase.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogVM @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val createCartUseCase: CreateCartUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val loadBucketIdUseCase: LoadBucketIdUseCase,
    private val saveBucketIdUseCase: SaveBucketIdUseCase,
    private val loadUserIdUseCase: LoadUserIdUseCase
) : ViewModel() {
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
                val bucket = loadBucketIdUseCase.invoke().isNotEmpty()
                _state.value = state.value.copy(
                    catalog = productsList.items.map {
                        Products(
                            id = it.id,
                            name = it.title,
                            price = it.price,
                            type = it.type,
                            inCart = false
                        )
                    },
                    goToCart = bucket
                )
            } catch (ex: Exception) {
                Log.e("server", ex.message.toString())
            }
        }
    }

    fun onEvent(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.EnteredSearch -> {
                _state.value = state.value.copy(
                    search = event.value
                )
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val products = getProductsUseCase.invoke(
                            filterField = "title",
                            filterValue = state.value.search
                        )
                        _state.value = state.value.copy(
                            catalog = products.items.map {
                                Products(
                                    id = it.id,
                                    name = it.title,
                                    price = it.price,
                                    type = it.type,
                                    inCart = false
                                )
                            }
                        )
                    } catch (ex: Exception) {
                        Log.e("search", ex.message.toString())
                    }
                }
            }

            CatalogEvent.FirstButtonClick -> {
                _state.value = state.value.copy(
                    firstActive = !state.value.firstActive
                )
                if (state.value.firstActive) {
                    _state.value = state.value.copy(
                        secondActive = false,
                        thirdActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Популярные")
                        } catch (ex: Exception) {
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }

            CatalogEvent.SecondButtonClick -> {
                _state.value = state.value.copy(
                    secondActive = !state.value.secondActive
                )
                if (state.value.secondActive) {
                    _state.value = state.value.copy(
                        firstActive = false,
                        thirdActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Женщинам")
                        } catch (ex: Exception) {
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }

            CatalogEvent.ThirdButtonClick -> {
                _state.value = state.value.copy(
                    thirdActive = !state.value.thirdActive
                )
                if (state.value.thirdActive) {
                    _state.value = state.value.copy(
                        firstActive = false,
                        secondActive = false
                    )
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            getCatalog(filterField = "type", "Мужчинам")
                        } catch (ex: Exception) {
                            Log.e("getProducts", ex.message.toString())
                        }
                    }
                }
            }

            CatalogEvent.ShowDescription -> {
                _state.value = state.value.copy(
                    showDescription = !state.value.showDescription
                )
            }

            is CatalogEvent.GetDescription -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val description = getProductByIdUseCase.invoke(event.value)
                        _state.value = state.value.copy(
                            description = description.description,
                            title = description.title,
                            productId = description.id,
                            approximateCost = description.approximateCost,
                            price = description.price
                        )
                    } catch (ex: Exception) {
                        Log.e("getProductById", ex.message.toString())
                    }
                }
            }

            is CatalogEvent.AddToCart -> {
                val product = state.value.catalog.find { it.id == event.value }
                if (product != null && product.inCart) {
                    addDeleteInCart(false, event.value)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        try {
                            val bucketId = loadBucketIdUseCase.invoke()
                            val userId = loadUserIdUseCase.invoke()
                            Log.i("info", "$bucketId, $userId")
                            if (bucketId.isEmpty()) {
                                val newBucketId = createCartUseCase.invoke(
                                    user_id = userId,
                                    product_id = event.value,
                                    count = 1
                                )
                                saveBucketIdUseCase.invoke(newBucketId.id)
                            } else {
                                updateCartUseCase.invoke(
                                    user_id = userId,
                                    product_id = event.value,
                                    count = 1,
                                    bucketId = bucketId
                                )
                            }
                            addDeleteInCart(true, event.value)
                        } catch (ex: Exception) {
                            Log.e("addToCart", ex.message.toString())
                        }
                    }
                }
            }
        }
    }

    private suspend fun getCatalog(filterField: String, filterValue: String) {
        val catalog = getProductsUseCase.invoke(filterField, filterValue)
        _state.value = state.value.copy(
            catalog = catalog.items.map {
                Products(
                    name = it.title,
                    type = it.type,
                    price = it.price,
                    id = it.id,
                    inCart = false
                )
            }
        )
    }

    private fun addDeleteInCart(add: Boolean, id: String) {
        val product = state.value.catalog.find { it.id == id }
        if(product != null) {
            val newCatalog = state.value.catalog.map {
                if (it.id == product.id) {
                    it.copy(
                        inCart = add
                    )
                } else {
                    it
                }
            }
            _state.value = state.value.copy(
                catalog = newCatalog
            )
        }
    }
}