package com.example.matuleclothes.presentation.Projects

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.R
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Buttons.Small.SmallActiveButton
import com.example.uikit.Presentation.CardStroke
import com.example.uikit.Presentation.InputIcon
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.TabBar
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun ProjectsScreen(navController: NavController, vm: ProjectsVM = hiltViewModel()) {
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
                    .padding(top = 28.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Проекты",
                        style = Theme.typography.title2Semibold,
                        color = Black,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    )
                    Icon(
                        painter = painterResource(com.example.uikit.R.drawable.icon_plus),
                        contentDescription = null,
                        tint = InputIcon,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(interactionSource = remember { MutableInteractionSource() }) {
                                ripple()
                                navController.navigate(Navigation.CreateProject)
                            }
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .fillMaxWidth()
                ) {
                    items(state.projectsList) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 136.dp)
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
                                Text(
                                    text = it.title,
                                    style = Theme.typography.headlineMedium,
                                    color = Black
                                )
                                Row(
                                    modifier = Modifier
                                        .padding(top = 44.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Прошло ${it.dateStart} дня",
                                        style = Theme.typography.captionSemibold,
                                        color = Placeholder
                                    )
                                    SmallActiveButton(
                                        onCLick = {},
                                        text = "Открыть"
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(15.dp)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
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