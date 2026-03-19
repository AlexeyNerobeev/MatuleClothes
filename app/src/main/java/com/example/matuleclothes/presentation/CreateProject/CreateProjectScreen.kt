package com.example.matuleclothes.presentation.CreateProject

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.Buttons.BigButtons.BigInactiveButton
import com.example.uikit.Presentation.Inputs.InputTF
import com.example.uikit.Presentation.Select
import com.example.uikit.Presentation.TabBar
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CreateProjectScreen(navController: NavController, vm: CreateProjectVM = hiltViewModel()) {
    val state = vm.state.value
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            vm.onEvent(CreateProjectEvent.LoadImageFromGallery(it))
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            vm.onEvent(CreateProjectEvent.LoadImageFromCamera(it))
        }
    }
    LaunchedEffect(key1 = state.isComplete) {
        if (state.isComplete) {
            navController.navigate(Navigation.Projects) {
                popUpTo(0)
            }
        }
    }
    LaunchedEffect(key1 = state.loadFromGallery) {
        if (state.loadFromGallery) {
            galleryLauncher.launch("image/*")
        }
    }
    LaunchedEffect(key1 = state.loadFromCamera) {
        if (state.loadFromCamera) {
            cameraLauncher.launch()
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = White)
        ) {
            Text(
                text = "Создать проект",
                style = Theme.typography.title2Semibold,
                color = Black,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 31.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                item {
                    Select(
                        title = "Выберите  тип",
                        value = state.type,
                        dropItems = listOf(
                            "Индивидуальный",
                            "Групповой"
                        ),
                        onValueChange = {
                            vm.onEvent(CreateProjectEvent.EnteredType(it))
                        },
                        icon = null,
                        description = "Тип",
                        withDismiss = false
                    )
                    InputTF(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        value = state.name,
                        onValueChange = {
                            vm.onEvent(CreateProjectEvent.EnteredName(it))
                        },
                        title = "Название проекта",
                        placeholder = "Введите имя",
                    )
                    InputTF(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        value = state.dateStart,
                        onValueChange = {
                            vm.onEvent(CreateProjectEvent.EnteredDateStart(it))
                        },
                        title = "Дата начала",
                        placeholder = "--.--.----",
                    )
                    InputTF(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        value = state.dateEnd,
                        onValueChange = {
                            vm.onEvent(CreateProjectEvent.EnteredDateEnd(it))
                        },
                        title = "Дата Окончания",
                        placeholder = "--.--.----",
                    )
                    Select(
                        title = "Выберите  кому",
                        value = state.toWhom,
                        dropItems = listOf(
                            "Себе",
                            "Команде"
                        ),
                        onValueChange = { vm.onEvent(CreateProjectEvent.EnteredToWhom(it)) },
                        modifier = Modifier
                            .padding(top = 16.dp),
                        icon = null,
                        description = "Кому",
                        withDismiss = false
                    )
                    InputTF(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        value = state.descriptionSource,
                        onValueChange = {
                            vm.onEvent(CreateProjectEvent.EnteredDescriptionSource(it))
                        },
                        title = "Источник описания",
                        placeholder = "example.com",
                    )
                    Select(
                        title = "Выберите  категорию",
                        value = state.category,
                        dropItems = listOf(
                            "Одежда",
                            "Аксессуары"
                        ),
                        onValueChange = { vm.onEvent(CreateProjectEvent.EnteredCategory(it)) },
                        modifier = Modifier
                            .padding(top = 16.dp),
                        icon = null,
                        description = "Категория",
                        withDismiss = false
                    )
                    Select(
                        title = "Загрузить",
                        value = if (state.technicalDrawing == null) {
                            ""
                        } else {
                            state.technicalDrawing.toString()
                        },
                        dropItems = listOf(
                            "Галерея",
                            "Камера"
                        ),
                        onValueChange = { vm.onEvent(CreateProjectEvent.EnteredTechnicalDrawing(it)) },
                        modifier = Modifier
                            .padding(top = 16.dp),
                        icon = null,
                        description = "Технический рисунок",
                        withDismiss = false
                    )
                    if (state.type.isNotEmpty() &&
                        state.name.isNotEmpty() &&
                        state.dateStart.isNotEmpty() &&
                        state.dateEnd.isNotEmpty() &&
                        state.toWhom.isNotEmpty() &&
                        state.descriptionSource.isNotEmpty() &&
                        state.category.isNotEmpty() &&
                        state.technicalDrawing != null
                    ) {
                        BigActiveButton(
                            onCLick = {
                                vm.onEvent(CreateProjectEvent.OnConfirm)
                            },
                            text = "Подтвердить",
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                    } else {
                        BigInactiveButton(
                            onCLick = {},
                            text = "Подтвердить",
                            modifier = Modifier
                                .padding(top = 16.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(100.dp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            TabBar(
                currentScreenNumber = 3,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onFirstIconCLick = { navController.navigate(Navigation.Main) },
                onSecondIconCLick = { navController.navigate(Navigation.Catalog) },
                onThirdIconCLick = {},
                onFourthIconCLick = {navController.navigate(Navigation.Profile)}
            )
        }
    }
}