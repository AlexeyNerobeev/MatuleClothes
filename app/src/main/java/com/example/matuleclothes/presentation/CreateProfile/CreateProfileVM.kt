package com.example.matuleclothes.presentation.CreateProfile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.usecase.EditUserUseCase
import com.example.network.domain.usecase.LoadEmailUseCase
import com.example.network.domain.usecase.LoadTokenUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateProfileVM @Inject constructor(
    private val editUserUseCase: EditUserUseCase,
    private val loadUserIdUseCase: LoadUserIdUseCase,
    private val loadEmailUseCase: LoadEmailUseCase
): ViewModel() {
    private val _state = mutableStateOf(CreateProfileState())
    val state: State<CreateProfileState> = _state

    fun onEvent(event: CreateProfileEvent){
        when(event){
            is CreateProfileEvent.EnteredBirthday -> {
                _state.value = state.value.copy(
                    birthday = event.value
                )
            }
            is CreateProfileEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is CreateProfileEvent.EnteredPatronymic -> {
                _state.value = state.value.copy(
                    patronymic = event.value
                )
            }
            is CreateProfileEvent.EnteredSurname -> {
                _state.value = state.value.copy(
                    surname = event.value
                )
            }
            is CreateProfileEvent.EnteredTelegram -> {
                _state.value = state.value.copy(
                    telegram = event.value
                )
            }
            is CreateProfileEvent.SelectGender -> {
                _state.value = state.value.copy(
                    gender = event.value
                )
            }
            CreateProfileEvent.CreateProfile -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val userId = loadUserIdUseCase.invoke()
                        val email = loadEmailUseCase.invoke()
                        Log.i("id email", "id: $userId, email: $email")
                        editUserUseCase.invoke(
                            userId = userId,
                            email = email,
                            firstName = state.value.name,
                            secondName = state.value.surname,
                            dateBirthday = state.value.birthday,
                            gender = state.value.gender
                        )
                        _state.value = state.value.copy(
                            isProfileCreate = true
                        )
                    } catch (ex: Exception) {
                        Log.e("createProfile", ex.message.toString())
                    }
                }
            }
        }
    }
}