package com.example.matuleclothes.presentation.Splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.network.domain.usecase.LoadTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val loadTokenUseCase: LoadTokenUseCase
): ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    init {
        val token = loadTokenUseCase.invoke()
        _state.value = state.value.copy(
            token = token
        )
    }
}