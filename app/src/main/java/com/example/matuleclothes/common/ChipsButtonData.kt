package com.example.matuleclothes.common

import androidx.compose.ui.Modifier

data class ChipsButtonsData(
    val text: String,
    val active: Boolean,
    val onCLick: () -> Unit,
    val modifier: Modifier
)