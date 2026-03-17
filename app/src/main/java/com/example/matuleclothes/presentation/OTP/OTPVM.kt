package com.example.matuleclothes.presentation.OTP

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OTPVM @Inject constructor(

): ViewModel() {
    private val _state = mutableStateOf(OTPState())
    val state: State<OTPState> = _state

    fun onEvent(event: OTPEvent){
        when(event){
            is OTPEvent.EnteredFirstNumber -> {
                _state.value = state.value.copy(
                    firstNumber = event.value
                )
            }
            is OTPEvent.EnteredFourthNumber -> {
                _state.value = state.value.copy(
                    fourthNumber = event.value
                )
            }
            is OTPEvent.EnteredSecondNumber -> {
                _state.value = state.value.copy(
                    secondNumber = event.value
                )
            }
            is OTPEvent.EnteredThirdNumber -> {
                _state.value = state.value.copy(
                    thirdNumber = event.value
                )
            }
            OTPEvent.IsAllFieldsFilled -> {
                if (state.value.firstNumber.isNotEmpty() &&
                    state.value.secondNumber.isNotEmpty() &&
                    state.value.thirdNumber.isNotEmpty() &&
                    state.value.fourthNumber.isNotEmpty()){
                    _state.value = state.value.copy(
                        isAllFieldFilled = true
                    )
                }
            }
        }
    }
}