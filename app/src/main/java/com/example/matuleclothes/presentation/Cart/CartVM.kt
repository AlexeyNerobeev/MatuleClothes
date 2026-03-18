package com.example.matuleclothes.presentation.Cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartVM @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    fun onEvent(event: CartEvent){
        when(event){
            CartEvent.OnMinus -> {
                if(state.value.count > 1) {
                    val newCount = state.value.count - 1
                    _state.value = state.value.copy(
                        count = newCount
                    )
                    calculateTotalSum()
                }
            }
            CartEvent.OnPlus -> {
                val newCount = state.value.count + 1
                _state.value = state.value.copy(
                    count = newCount
                )
                calculateTotalSum()
            }
        }
    }

    private fun calculateTotalSum(){
        val sum = state.value.count * 300 * 2
        _state.value = state.value.copy(
            totalSum = sum
        )
    }
}