package com.example.matuleclothes.presentation.OTP

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.presentation.OTP.common.OTPTFData
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.Bubble.BackButton
import com.example.uikit.Presentation.Inputs.OTPTF
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun OTPScreen(navController: NavController, vm: OTPVM = hiltViewModel()) {
    val state = vm.state.value
    val tfList = listOf(
        OTPTFData(
            modifier = Modifier,
            value = state.firstNumber,
            onValueChange = { vm.onEvent(OTPEvent.EnteredFirstNumber(it))}
        ),
        OTPTFData(
            modifier = Modifier,
            value = state.secondNumber,
            onValueChange = { vm.onEvent(OTPEvent.EnteredSecondNumber(it))
            vm.onEvent(OTPEvent.IsAllFieldsFilled)}
        ),
        OTPTFData(
            modifier = Modifier,
            value = state.thirdNumber,
            onValueChange = { vm.onEvent(OTPEvent.EnteredThirdNumber(it))
                vm.onEvent(OTPEvent.IsAllFieldsFilled)}
        ),
        OTPTFData(
            modifier = Modifier,
            value = state.fourthNumber,
            onValueChange = { vm.onEvent(OTPEvent.EnteredFourthNumber(it))
                vm.onEvent(OTPEvent.IsAllFieldsFilled)}
        )
    )
    LaunchedEffect(key1 = state.isAllFieldFilled) {
        if(state.isAllFieldFilled){
            navController.navigate(Navigation.CreatePassword)
        }
    }
    Scaffold(modifier = Modifier
        .fillMaxSize()){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)){
            BackButton(
                onCLick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(start = 20.dp)
            )
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 67.dp)) {
                    Text(text = "Введите код из Telegram",
                        color = Black,
                        style = Theme.typography.title3Semibold,
                        textAlign = TextAlign.Center
                    )
                    Row(modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        tfList.forEach {
                            OTPTF(
                                modifier = it.modifier,
                                value = it.value,
                                onValueChange = it.onValueChange
                            )
                        }
                    }
                    Text(text = "Отправить код повторно можно\nбудет через 55 секунд",
                        color = Placeholder,
                        style = Theme.typography.textRegular,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}