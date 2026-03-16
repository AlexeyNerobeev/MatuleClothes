package com.example.matuleclothes.presentation.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.uikit.Presentation.Accent
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.Buttons.BigButtons.BigInactiveButton
import com.example.uikit.Presentation.Buttons.LogIn.VKButton
import com.example.uikit.Presentation.Buttons.LogIn.YandexButton
import com.example.uikit.Presentation.Description
import com.example.uikit.Presentation.InputBG
import com.example.uikit.Presentation.InputIcon
import com.example.uikit.Presentation.InputStroke
import com.example.uikit.Presentation.Inputs.IncorrectInputTF
import com.example.uikit.Presentation.Inputs.InputTF
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun SignInScreen(navController: NavController, vm: SignInVM = hiltViewModel()) {
    val state = vm.state.value
    LaunchedEffect(key1 = state.isLogin, key2 = state.isRegister) {
        if (state.isLogin) {
            navController.navigate(Navigation.Main)
        }
        if(state.isRegister){
            navController.navigate(Navigation.CreateProfile)
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
            Column(
                modifier = Modifier
                    .padding(top = 59.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.hello_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                    )
                    Text(
                        text = "Добро пожаловать!",
                        color = Black,
                        style = Theme.typography.title1Heavy,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )
                }
                Text(
                    text = "Войдите, чтобы пользоваться функциями\nприложения",
                    color = Black,
                    style = Theme.typography.textRegular,
                    modifier = Modifier
                        .padding(top = 23.dp)
                )
                if (!state.isEmptyEmail) {
                    InputTF(
                        modifier = Modifier
                            .padding(top = 64.dp),
                        value = state.email,
                        onValueChange = {
                            vm.onEvent(SignInEvent.EnteredEmail(it))
                        },
                        title = "Вход по E-mail",
                        placeholder = "example@mail.com"
                    )
                } else {
                    IncorrectInputTF(
                        modifier = Modifier
                            .padding(top = 64.dp),
                        value = state.email,
                        onValueChange = {
                            vm.onEvent(SignInEvent.EnteredEmail(it))
                        },
                        placeholder = "example@mail.com",
                        message = "Введите E-mail"
                    )
                }
                if (!state.isEmptyPassword) {
                    Column(
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Пароль",
                            style = Theme.typography.captionRegular,
                            color = Description,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = state.password,
                            onValueChange = {
                                vm.onEvent(SignInEvent.EnteredPassword(it))
                            },
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
                                    onClick = {
                                        vm.onEvent(SignInEvent.VisualChange)
                                    }
                                ) {
                                    Icon(
                                        painter = if (state.visual) {
                                            painterResource(R.drawable.no_eye_icon)
                                        } else {
                                            painterResource(R.drawable.eye_icon)
                                        },
                                        contentDescription = null,
                                        tint = Black
                                    )
                                }
                            },
                            visualTransformation = if (state.visual) {
                                PasswordVisualTransformation('*')
                            } else {
                                VisualTransformation.None
                            }
                        )
                    }
                } else {
                    IncorrectInputTF(
                        value = state.password,
                        onValueChange = {
                            vm.onEvent(SignInEvent.EnteredPassword(it))
                        },
                        modifier = Modifier
                            .padding(top = 14.dp)
                            .fillMaxWidth()
                            .heightIn(48.dp),
                        placeholder = "",
                        message = "Введите пароль"
                    )
                }
                if (state.email.isEmpty() || state.password.isEmpty()) {
                    BigInactiveButton(
                        onCLick = {
                            vm.onEvent(SignInEvent.IsFieldsEmpty)
                        },
                        text = "Далее",
                        modifier = Modifier
                            .padding(top = 14.dp)
                    )
                } else {
                    BigActiveButton(
                        onCLick = {
                            vm.onEvent(SignInEvent.Login)
                        },
                        text = "Далее",
                        modifier = Modifier
                            .padding(top = 14.dp)
                    )
                }
                Text(
                    text = "Забыл пароль?",
                    color = Accent,
                    style = Theme.typography.textRegular,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable{
                            navController.navigate(Navigation.OTP)
                        }
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(bottom = 56.dp)
                    ) {
                        Text(
                            text = "Или войдите с помощью",
                            color = Placeholder,
                            style = Theme.typography.textRegular
                        )
                        VKButton(
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {

                        }
                        YandexButton(
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {

                        }
                    }
                }
            }
        }
    }
}