package com.example.matuleclothes.presentation.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.R
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.CardStroke
import com.example.uikit.Presentation.Description
import com.example.uikit.Presentation.Headers.ColumnHeader
import com.example.uikit.Presentation.InputBG
import com.example.uikit.Presentation.InputIcon
import com.example.uikit.Presentation.InputStroke
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CartScreen(navController: NavController, vm: CartVM = hiltViewModel()) {
    val state = vm.state.value
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
                    .padding(horizontal = 20.dp)
                    .padding(top = 16.dp)
                    .fillMaxSize()
            ) {
                ColumnHeader(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onDeleteClick = { },
                    title = "Корзина"
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth()
                ) {
                    items(2) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp, color = CardStroke,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Рубашка воскресенье для машинного вязания",
                                        color = Black,
                                        style = Theme.typography.headlineMedium,
                                        modifier = Modifier
                                            .padding(end = 28.dp)
                                    )
                                    IconButton(
                                        onClick = {

                                        },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                    ) {
                                        Icon(
                                            painter = painterResource(com.example.uikit.R.drawable.icon_close),
                                            contentDescription = null,
                                            tint = Description
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier
                                        .padding(top = 34.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "300 ₽",
                                        style = Theme.typography.title3Medium,
                                        color = Black
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = "${state.count} штук",
                                            color = Black,
                                            style = Theme.typography.textRegular
                                        )
                                        Box(
                                            modifier = Modifier
                                                .padding(start = 42.dp)
                                                .size(64.dp, 32.dp)
                                                .background(
                                                    color = InputBG,
                                                    shape = RoundedCornerShape(8.dp)
                                                )
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(6.dp)
                                                    .fillMaxSize(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    painter = painterResource(com.example.uikit.R.drawable.icon_minus),
                                                    contentDescription = null,
                                                    tint = InputIcon,
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .clickable {
                                                            vm.onEvent(CartEvent.OnMinus)
                                                        }
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .size(1.dp, 16.dp)
                                                        .background(color = InputStroke)
                                                )
                                                Icon(
                                                    painter = painterResource(com.example.uikit.R.drawable.icon_plus),
                                                    contentDescription = null,
                                                    tint = Placeholder,
                                                    modifier = Modifier
                                                        .size(20.dp)
                                                        .clickable {
                                                            vm.onEvent(CartEvent.OnPlus)
                                                        }
                                                )

                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Сумма",
                        style = Theme.typography.title2Semibold,
                        color = Black
                    )
                    Text(
                        text = "${state.totalSum} ₽",
                        style = Theme.typography.title2Semibold,
                        color = Black
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
            BigActiveButton(
                onCLick = { },
                text = "Перейти к оформлению заказа",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 32.dp)
            )
        }
    }
}