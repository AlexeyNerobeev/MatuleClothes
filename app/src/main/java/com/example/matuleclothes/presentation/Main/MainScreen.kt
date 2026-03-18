package com.example.matuleclothes.presentation.Main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.R
import com.example.matuleclothes.common.ChipsButtonsData
import com.example.uikit.Presentation.Buttons.Chips.ChipActiveButton
import com.example.uikit.Presentation.Buttons.Chips.ChipInactiveButton
import com.example.uikit.Presentation.Cards.Primary
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.Search
import com.example.uikit.Presentation.TabBar
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun MainScreen(navController: NavController, vm: MainVM = hiltViewModel()) {
    val state = vm.state.value
    val chipsList = listOf(
        ChipsButtonsData(
            text = "Популярные",
            active = state.firstActive,
            onCLick = {vm.onEvent(MainEvent.FirstButtonClick)},
            modifier = Modifier
        ),
        ChipsButtonsData(
            text = "Женщинам",
            active = state.secondActive,
            onCLick = {vm.onEvent(MainEvent.SecondButtonClick)},
            modifier = Modifier
                .padding(start = 16.dp)
        ),
        ChipsButtonsData(
            text = "Мужчинам",
            active = state.thirdActive,
            onCLick = {vm.onEvent(MainEvent.ThirdButtonClick)},
            modifier = Modifier
                .padding(start = 16.dp)
        )
    )
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)) {
            Search(
                value = state.search,
                onValueChange = {vm.onEvent(MainEvent.EnteredSearch(it))},
                modifier = Modifier
                    .padding(top = 24.dp)
                    .padding(horizontal = 20.dp),
                withCancel = false,
                onCancelClick = {},
                placeholder = "Искать  описания"
            )
            Text(text = "Акции и новости",
                color = Placeholder,
                style = Theme.typography.title3Semibold,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(start = 20.dp)
            )
            LazyRow(modifier = Modifier
                .padding(top = 16.dp)
                .padding(start = 20.dp)
                .fillMaxWidth()) {
                items(state.productsList) { item ->
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(270.dp, 152.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF76B3FF),
                                        Color(0xFFCDE3FF)
                                    )
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Image(painter = painterResource(R.drawable.sales_image),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.BottomEnd))
                        Text(text = item.name,
                            color = White,
                            style = Theme.typography.title2Heavy,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 16.dp)
                                .padding(start = 16.dp)
                                .padding(end = 78.dp))
                        Text(text = "${item.price}₽",
                            color = White,
                            style = Theme.typography.title2Heavy,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 16.dp)
                                .padding(bottom = 12.dp))
                    }
                }
            }
            Text(text = "Каталог описаний",
                color = Placeholder,
                style = Theme.typography.title3Semibold,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(start = 21.dp))
            LazyRow(modifier = Modifier
                .padding(top = 15.dp)
                .padding(start = 16.dp)
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
                            .padding(bottom = 16.dp),
                        title = it.name,
                        description = it.type,
                        price = it.price,
                        inCart = it.inCart,
                        onCLick = {

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
            TabBar(
                currentScreenNumber = 1,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onFirstIconCLick = {},
                onSecondIconCLick = {navController.navigate(Navigation.Catalog)},
                onThirdIconCLick = {navController.navigate(Navigation.Projects)},
                onFourthIconCLick = {}
            )
        }
    }
}