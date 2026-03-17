package com.example.matuleclothes.presentation.CreatePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.R
import com.example.matuleclothes.presentation.CreatePassword.common.PasswordTFData
import com.example.matuleclothes.presentation.SignIn.SignInEvent
import com.example.uikit.Presentation.Accent
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.Description
import com.example.uikit.Presentation.InputBG
import com.example.uikit.Presentation.InputIcon
import com.example.uikit.Presentation.InputStroke
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CreatePasswordScreen(navController: NavController, vm: CreatePasswordVM = hiltViewModel()) {
    val state = vm.state.value
    val tfList = listOf(
        PasswordTFData(
            modifier = Modifier.padding(top = 90.dp),
            value = state.password,
            onValueChange = {vm.onEvent(CreatePasswordEvent.EnteredPassword(it))},
            title = "Новый Пароль",
            visual = state.firstVisual,
            onVisualChange = {vm.onEvent(CreatePasswordEvent.FirstVisualChange)}
        ),
        PasswordTFData(
            modifier = Modifier.padding(top = 12.dp),
            value = state.repeatPassword,
            onValueChange = {vm.onEvent(CreatePasswordEvent.EnteredRepeatPassword(it))},
            title = "Повторите Пароль",
            visual = state.secondVisual,
            onVisualChange = {vm.onEvent(CreatePasswordEvent.SecondVisualChange)}
        )
    )
    Scaffold(modifier = Modifier
        .fillMaxSize()){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)){
            Column(modifier = Modifier
                .padding(top = 59.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.hello_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp))
                    Text(text = "Создание пароля",
                        color = Black,
                        style = Theme.typography.title1Heavy,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
                Text(text = "Введите новый пароль",
                    color = Black,
                    style = Theme.typography.textRegular,
                    modifier = Modifier
                        .padding(top = 23.dp))
                tfList.forEach {
                    Column(
                        modifier = it.modifier
                    ) {
                        Text(
                            text = it.title,
                            style = Theme.typography.captionRegular,
                            color = Description,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = it.value,
                            onValueChange = it.onValueChange,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(48.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = InputBG,
                                unfocusedContainerColor = InputBG,
                                focusedPlaceholderColor = Placeholder,
                                unfocusedPlaceholderColor = Placeholder,
                                focusedTextColor = Black,
                                unfocusedTextColor = Black,
                                focusedBorderColor = Color(0xFF2254F5).copy(alpha = 0.5f),
                                unfocusedBorderColor = if (state.password.isEmpty()) {
                                    InputStroke
                                } else {
                                    InputIcon
                                },
                                cursorColor = Accent
                            ),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            textStyle = Theme.typography.textRegular,
                            trailingIcon = {
                                IconButton(
                                    onClick = it.onVisualChange
                                ) {
                                    Icon(
                                        painter = if (it.visual) {
                                            painterResource(R.drawable.no_eye_icon)
                                        } else {
                                            painterResource(R.drawable.eye_icon)
                                        },
                                        contentDescription = null,
                                        tint = Black
                                    )
                                }
                            },
                            visualTransformation = if (it.visual) {
                                PasswordVisualTransformation('*')
                            } else {
                                VisualTransformation.None
                            }
                        )
                    }
                }
                BigActiveButton(
                    onCLick = {
                        navController.navigate(Navigation.Main)
                    },
                    text = "Далее",
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }
        }
    }
}