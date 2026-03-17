package com.example.matuleclothes.presentation.CreatePassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePasswordVM @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(CreatePasswordState())
    val state: State<CreatePasswordState> = _state

    fun onEvent(event: CreatePasswordEvent){
        when(event){
            is CreatePasswordEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }
            is CreatePasswordEvent.EnteredRepeatPassword -> {
                _state.value = state.value.copy(
                    repeatPassword = event.value
                )
            }
            CreatePasswordEvent.FirstVisualChange -> {
                _state.value = state.value.copy(
                    firstVisual = !state.value.firstVisual
                )
            }
            CreatePasswordEvent.SecondVisualChange -> {
                _state.value = state.value.copy(
                    secondVisual = !state.value.secondVisual
                )
            }
        }
    }
}