package com.example.matuleclothes.presentation.CreateProfile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.matuleclothes.presentation.CreateProfile.common.InputTFData
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.Buttons.BigButtons.BigInactiveButton
import com.example.uikit.Presentation.Inputs.InputTF
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Select
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CreateProfileScreen(navController: NavController, vm: CreateProfileVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = state.isProfileCreate) {
        if(state.isProfileCreate){
            navController.navigate(Navigation.Main)
        }
    }
    val tfList = listOf(
        InputTFData(
            modifier = Modifier
                .padding(top = 32.dp),
            value = state.name,
            onValueChange = {
                vm.onEvent(CreateProfileEvent.EnteredName(it))
            },
            title = null,
            placeholder = "Имя",
            incorrectMessage = null
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp),
            value = state.patronymic,
            onValueChange = {
                vm.onEvent(CreateProfileEvent.EnteredPatronymic(it))
            },
            title = null,
            placeholder = "Отчество",
            incorrectMessage = null
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp),
            value = state.surname,
            onValueChange = {
                vm.onEvent(CreateProfileEvent.EnteredSurname(it))
            },
            title = null,
            placeholder = "Фамилия",
            incorrectMessage = null
        ),
        InputTFData(
            modifier = Modifier
                .padding(top = 24.dp),
            value = state.birthday,
            onValueChange = {
                vm.onEvent(CreateProfileEvent.EnteredBirthday(it))
            },
            title = null,
            placeholder = "Дата рождения",
            incorrectMessage = null
        )
    )
    Scaffold(modifier = Modifier
        .fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)) {
            Column(modifier = Modifier
                .padding(vertical = 32.dp)
                .padding(horizontal = 20.dp)
                .fillMaxSize()) {
                Text(text = "Создание Профиля",
                    color = Black,
                    style = Theme.typography.title1Heavy
                )
                Text(text = "Без профиля вы не сможете создавать проекты.",
                    color = Placeholder,
                    style = Theme.typography.captionRegular,
                    modifier = Modifier
                        .padding(top = 44.dp)
                )
                Text(text = "В профиле будут храниться результаты проектов и\nваши описания.",
                    color = Placeholder,
                    style = Theme.typography.captionRegular,
                    modifier = Modifier
                        .padding(top = 8.dp))
                tfList.forEach {
                    InputTF(
                        modifier = it.modifier,
                        value = it.value,
                        onValueChange = it.onValueChange,
                        title = it.title,
                        placeholder = it.placeholder
                    )
                }
                Select(
                    title = "Пол",
                    value = state.gender,
                    dropItems = listOf(
                        "Мужской",
                        "Женский"
                    ),
                    onValueChange = {
                        vm.onEvent(CreateProfileEvent.SelectGender(it))
                    },
                    modifier = Modifier
                        .padding(top = 12.dp),
                    icon = null,
                    description = null,
                    withDismiss = false
                )
                InputTF(
                    modifier = Modifier
                        .padding(top = 24.dp),
                    value = state.telegram,
                    onValueChange = {
                        vm.onEvent(CreateProfileEvent.EnteredTelegram(it))
                    },
                    title = null,
                    placeholder = "Telegram"
                )
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter) {
                    if (state.name.isEmpty() ||
                        state.patronymic.isEmpty() ||
                        state.surname.isEmpty() ||
                        state.birthday.isEmpty() ||
                        state.gender.isEmpty() ||
                        state.telegram.isEmpty()){
                        BigInactiveButton(
                            onCLick = {

                            },
                            text = "Создать"
                        )
                    } else{
                        BigActiveButton(
                            onCLick = {
                                vm.onEvent(CreateProfileEvent.CreateProfile)
                            },
                            text = "Создать"
                        )
                    }
                }
            }
        }
    }
}