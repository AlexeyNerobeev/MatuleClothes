package com.example.matuleclothes.presentation.SignIn

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.usecase.IsEmailValidUseCase
import com.example.matuleclothes.domain.usecase.IsPasswordValidUseCase
import com.example.network.domain.usecase.LoginUseCase
import com.example.network.domain.usecase.RegisterUseCase
import com.example.network.domain.usecase.SaveEmailUseCase
import com.example.network.domain.usecase.SaveTokenUseCase
import com.example.network.domain.usecase.SaveUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val registerUseCase: RegisterUseCase,
    private val isEmailValidUseCase: IsEmailValidUseCase,
    private val isPasswordValidUseCase: IsPasswordValidUseCase,
    private val saveEmailUseCase: SaveEmailUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignInState())
    val state: State<SignInState> = _state

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is SignInEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    password = event.value
                )
            }

            SignInEvent.VisualChange -> {
                _state.value = state.value.copy(
                    visual = !state.value.visual
                )
            }

            SignInEvent.IsFieldsEmpty -> {
                if (state.value.email.isEmpty()) {
                    _state.value = state.value.copy(
                        isEmptyEmail = true
                    )
                } else {
                    _state.value = state.value.copy(
                        isEmptyEmail = false
                    )
                }
                if (state.value.password.isEmpty()) {
                    _state.value = state.value.copy(
                        isEmptyPassword = true
                    )
                } else {
                    _state.value = state.value.copy(
                        isEmptyPassword = false
                    )
                }
            }

            SignInEvent.Login -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        login()
                    } catch (ex: Exception) {
                        Log.e("login", ex.message.toString())
                        try {
                            registration()
                        } catch (ex: Exception) {
                            Log.e("register", ex.message.toString())
                        }
                    }
                }
            }
        }
    }

    private suspend fun login(){
        val responseLogin = loginUseCase.invoke(
            identity = state.value.email,
            password = state.value.password
        )
        if (responseLogin.token.isNotEmpty()) {
            saveTokenUseCase.invoke(
                token = responseLogin.token,
                id = responseLogin.record.id
            )
            saveUserIdUseCase.invoke(responseLogin.record.id)
            saveEmailUseCase.invoke(state.value.email)
            _state.value = state.value.copy(
                isLogin = true
            )
        }
    }

    private suspend fun registration(){
        if (isEmailValidUseCase.invoke(state.value.email) &&
            isPasswordValidUseCase.invoke(state.value.password)
        ) {
            val responseRegister = registerUseCase.invoke(
                email = state.value.email,
                password = state.value.password,
                passwordConfirm = state.value.password
            )
            Log.i("responseRegister", responseRegister.id)
            if (responseRegister.id.isNotEmpty()) {
                login()
                _state.value = state.value.copy(
                    isRegister = true
                )
            }
        }
    }
}