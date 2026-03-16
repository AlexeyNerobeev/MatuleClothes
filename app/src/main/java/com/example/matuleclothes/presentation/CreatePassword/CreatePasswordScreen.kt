package com.example.matuleclothes.presentation.CreatePassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.matuleclothes.R
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CreatePasswordScreen(navController: NavController) {
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

            }
        }
    }
}