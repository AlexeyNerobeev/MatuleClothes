package com.example.matuleclothes.presentation.Catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.R
import com.example.matuleclothes.common.ChipsButtonsData
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.BigButtons.BigActiveButton
import com.example.uikit.Presentation.Buttons.CartButton
import com.example.uikit.Presentation.Buttons.Chips.ChipActiveButton
import com.example.uikit.Presentation.Buttons.Chips.ChipInactiveButton
import com.example.uikit.Presentation.Cards.Primary
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Search
import com.example.uikit.Presentation.TabBar
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun CatalogScreen(navController: NavController, vm: CatalogVM = hiltViewModel()) {
    val state = vm.state.value
    val chipsList = listOf(
        ChipsButtonsData(
            text = "Популярные",
            active = state.firstActive,
            onCLick = {vm.onEvent(CatalogEvent.FirstButtonClick)},
            modifier = Modifier
        ),
        ChipsButtonsData(
            text = "Женщинам",
            active = state.secondActive,
            onCLick = {vm.onEvent(CatalogEvent.SecondButtonClick)},
            modifier = Modifier
                .padding(start = 16.dp)
        ),
        ChipsButtonsData(
            text = "Мужчинам",
            active = state.thirdActive,
            onCLick = {vm.onEvent(CatalogEvent.ThirdButtonClick)},
            modifier = Modifier
                .padding(start = 16.dp)
        )
    )
    Scaffold(modifier = Modifier
        .fillMaxSize()){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)){
            Box(modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()) {
                Search(
                    value = state.search,
                    onValueChange = {vm.onEvent(CatalogEvent.EnteredSearch(it))},
                    withCancel = false,
                    onCancelClick = {},
                    placeholder = "Искать описания",
                    modifier = Modifier
                        .padding(end = 70.dp)
                )
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(painter = painterResource(R.drawable.user_icon),
                        contentDescription = null,
                        tint = Black
                    )
                }
            }
            LazyRow(modifier = Modifier
                .padding(top = 32.dp)
                .padding(start = 20.dp)
                .fillMaxWidth()) {
                items(chipsList) {
                    if (it.active){
                        ChipActiveButton(
                            onCLick = it.onCLick,
                            text = it.text,
                            modifier = it.modifier
                        )
                    } else{
                        ChipInactiveButton(
                            onCLick = it.onCLick,
                            text = it.text,
                            modifier = it.modifier
                        )
                    }
                }
            }
            LazyColumn(modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()) {
                item {
                    Spacer(modifier = Modifier
                        .height(24.dp))
                }
                items(state.catalog){
                    Primary(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .clickable(interactionSource = remember { MutableInteractionSource()}){
                                ripple()
                                vm.onEvent(CatalogEvent.GetDescription(it.id))
                                vm.onEvent(CatalogEvent.ShowDescription)
                            },
                        title = it.name,
                        description = it.type,
                        price = it.price,
                        inCart = it.inCart,
                        onCLick = {
                            vm.onEvent(CatalogEvent.AddToCart(it.id))
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier
                        .height(100.dp))
                }
            }
        }
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            Column(modifier = Modifier
                .fillMaxWidth()) {
                if(state.goToCart){
                    CartButton(
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .padding(horizontal = 20.dp),
                        title = "В корзину",
                        price = 500,
                        onCLick = {
                            navController.navigate(Navigation.Cart)
                        }
                    )
                }
                TabBar(
                    currentScreenNumber = 2,
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    onFirstIconCLick = { navController.navigate(Navigation.Main) },
                    onSecondIconCLick = {},
                    onThirdIconCLick = {navController.navigate(Navigation.Projects)},
                    onFourthIconCLick = {navController.navigate(Navigation.Profile)}
                )
            }
        }
        if(state.showDescription) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 24.dp,
                                topEnd = 24.dp
                            )
                        )
                        .background(color = White)
                ) {
                    Column(modifier = Modifier
                        .padding(top = 24.dp)
                        .padding(bottom = 40.dp)
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()) {
                        Box(modifier = Modifier
                            .fillMaxWidth()) {
                            Text(text = state.title,
                                style = Theme.typography.title2Semibold,
                                color = Black,
                                modifier = Modifier
                                    .padding(end = 68.dp))
                            IconButton(
                                onClick = {
                                    vm.onEvent(CatalogEvent.ShowDescription)
                                },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                            ) {
                                Icon(painter = painterResource(com.example.uikit.R.drawable.icon_dismiss),
                                    contentDescription = null,
                                    tint = Color.Unspecified)
                            }
                        }
                        Text(text = "Описание",
                            style = Theme.typography.headlineMedium,
                            color = Placeholder,
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )
                        Text(text = state.description,
                            color = Black,
                            style = Theme.typography.textRegular,
                            modifier = Modifier
                                .padding(top = 8.dp))
                        Box(modifier = Modifier
                            .padding(top = 89.dp)
                            .fillMaxWidth(),
                            contentAlignment = Alignment.BottomCenter
                        ){
                            Column(modifier = Modifier
                                .fillMaxWidth()) {
                                Text(text = "Примерный расход:",
                                    style = Theme.typography.captionSemibold,
                                    color = Placeholder)
                                Text(text = "${state.approximateCost} г",
                                    color = Black,
                                    style = Theme.typography.headlineMedium,
                                    modifier = Modifier
                                        .padding(top = 4.dp))
                                BigActiveButton(
                                    onCLick = {
                                        vm.onEvent(CatalogEvent.AddToCart(state.productId))
                                    },
                                    text = "Добавить за ${state.price} ₽",
                                    modifier = Modifier
                                        .padding(top = 35.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}