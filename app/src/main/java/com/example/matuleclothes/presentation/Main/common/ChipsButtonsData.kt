package com.example.matuleclothes.presentation.Main.common

import androidx.compose.ui.Modifier

data class ChipsButtonsData(
    val text: String,
    val active: Boolean,
    val onCLick: () -> Unit,
    val modifier: Modifier
)