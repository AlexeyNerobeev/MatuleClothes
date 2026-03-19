package com.example.matuleclothes.presentation.Profile

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.usecase.DeleteTokenUseCase
import com.example.network.domain.usecase.GetUserUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val loadUserIdUseCase: LoadUserIdUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase
): ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadUserIdUseCase.invoke()
                val user = getUserUseCase.invoke(userId)
                _state.value = state.value.copy(
                    name = user.firstname,
                    phone = "+7 967 078-58-37"
                )
            } catch (e: Exception){
                Log.e("server", e.message.toString())
            }
        }
    }

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.OnNotificationsChange -> {
                _state.value = state.value.copy(
                    notifications = !state.value.notifications
                )
            }
            ProfileEvent.OnExit -> {
                try {
                    deleteTokenUseCase.invoke()
                } catch (e: Exception){
                    Log.e("exit", e.message.toString())
                }
            }
        }
    }
}