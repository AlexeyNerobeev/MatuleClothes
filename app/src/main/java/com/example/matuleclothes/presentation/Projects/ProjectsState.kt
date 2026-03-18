package com.example.matuleclothes.presentation.Projects

import com.example.matuleclothes.domain.model.Projects

data class ProjectsState(
    val projectsList: List<Projects> = listOf()
)