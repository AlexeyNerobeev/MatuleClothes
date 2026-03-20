package com.example.matuleclothes.Widget

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.model.Projects
import com.example.network.domain.usecase.GetProjectsUseCase
import com.example.network.domain.usecase.LoadUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WidgetVM @Inject constructor(
    private val getProjectsUseCase: GetProjectsUseCase,
    private val loadUserIdUseCase: LoadUserIdUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WidgetState())
    val state: State<WidgetState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadUserIdUseCase.invoke()
                val project = getProjectsUseCase.invoke().items.find { it.user_id == userId }
                if (project != null) {
                    _state.value = state.value.copy(
                        project = Projects(
                            id = project.id,
                            title = project.title,
                            dateStart = project.dateStart
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}