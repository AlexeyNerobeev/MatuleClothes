package com.example.matuleclothes.presentation.CreateProject

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.domain.usecase.CreateProjectUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateProjectVM @Inject constructor(
    private val createProjectUseCase: CreateProjectUseCase,
    private val loadUserIdUseCase: LoadUserIdUseCase,
    @param:ApplicationContext
    private val context: Context
): ViewModel() {
    private val _state = mutableStateOf(CreateProjectState())
    val state: State<CreateProjectState> = _state

    fun onEvent(event: CreateProjectEvent){
        when(event){
            is CreateProjectEvent.EnteredCategory -> {
                _state.value = state.value.copy(
                    category = event.value
                )
            }
            is CreateProjectEvent.EnteredDateEnd -> {
                _state.value = state.value.copy(
                    dateEnd = event.value
                )
            }
            is CreateProjectEvent.EnteredDateStart -> {
                _state.value = state.value.copy(
                    dateStart = event.value
                )
            }
            is CreateProjectEvent.EnteredDescriptionSource -> {
                _state.value = state.value.copy(
                    descriptionSource = event.value
                )
            }
            is CreateProjectEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }
            is CreateProjectEvent.EnteredTechnicalDrawing -> {
                if(event.value == "Галерея"){
                    _state.value = state.value.copy(
                        loadFromGallery = true
                    )
                } else if(event.value == "Камера"){
                    _state.value = state.value.copy(
                        loadFromCamera = true
                    )
                }
            }
            is CreateProjectEvent.EnteredToWhom -> {
                _state.value = state.value.copy(
                    toWhom = event.value
                )
            }
            is CreateProjectEvent.EnteredType -> {
                _state.value = state.value.copy(
                    type = event.value
                )
            }
            CreateProjectEvent.OnConfirm -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val userId = loadUserIdUseCase.invoke()
                        createProjectUseCase.invoke(
                            title = state.value.name,
                            typeProject = state.value.type,
                            user_id = userId,
                            dateStart = state.value.dateStart,
                            dateEnd = state.value.dateEnd,
                            gender = state.value.toWhom,
                            description_source = state.value.descriptionSource,
                            category = state.value.category,
                            image = state.value.technicalDrawing.toString()
                        )
                        _state.value = state.value.copy(
                            isComplete = true
                        )
                    }catch (e: Exception){
                        Log.e("createProject", e.message.toString())
                    }
                }
            }
            is CreateProjectEvent.LoadImageFromCamera -> {
                _state.value = state.value.copy(
                    technicalDrawing = event.value,
                    loadFromCamera = false
                )
            }
            is CreateProjectEvent.LoadImageFromGallery -> {
                viewModelScope.launch {
                    val bitmap = withContext(Dispatchers.IO){
                        val stream = context.contentResolver.openInputStream(event.value)
                        BitmapFactory.decodeStream(stream)
                    }
                    _state.value = state.value.copy(
                        technicalDrawing = bitmap
                    )
                }
            }
        }
    }
}