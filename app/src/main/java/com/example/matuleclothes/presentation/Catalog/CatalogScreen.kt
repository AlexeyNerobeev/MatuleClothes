package com.example.matuleclothes.presentation.Catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.R
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Search
import com.example.uikit.Presentation.White

@Composable
fun CatalogScreen(navController: NavController, vm: CatalogVM = hiltViewModel()) {
    val state = vm.state.value
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
        }
    }
}