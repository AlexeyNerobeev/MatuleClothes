package com.example.matuleclothes.presentation.Projects

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matuleclothes.domain.model.Projects
import com.example.network.domain.usecase.GetProjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsVM @Inject constructor(
    private val getProjectsUseCase: GetProjectsUseCase
): ViewModel() {
    private val _state = mutableStateOf(ProjectsState())
    val state: State<ProjectsState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val projectsList = getProjectsUseCase.invoke()
                _state.value = state.value.copy(
                    projectsList = projectsList.items.map {
                        Projects(id = it.id,
                            title = it.title,
                            dateStart = it.dateStart)
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}