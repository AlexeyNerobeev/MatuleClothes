package com.example.matuleclothes.presentation.Profile

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.matuleclothes.Navigation
import com.example.matuleclothes.R
import com.example.uikit.Presentation.Accent
import com.example.uikit.Presentation.Black
import com.example.uikit.Presentation.Error
import com.example.uikit.Presentation.InputIcon
import com.example.uikit.Presentation.Placeholder
import com.example.uikit.Presentation.TabBar
import com.example.uikit.Presentation.Theme
import com.example.uikit.Presentation.White

@Composable
fun ProfileScreen(navController: NavController, vm: ProfileVM = hiltViewModel()) {
    val state = vm.state.value
    val context = LocalContext.current
    Scaffold(modifier = Modifier
        .fillMaxSize()){ innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(color = White)){
            Column(modifier = Modifier
                .padding(top = 32.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()) {
                Text(text = state.name,
                    style = Theme.typography.title1Heavy,
                    color = Black
                )
                Text(text = state.phone,
                    style = Theme.typography.headlineRegular,
                    color = Placeholder,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Row(modifier = Modifier
                    .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.orders_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp))
                    Text(text = "Мои заказы",
                        style = Theme.typography.title3Semibold,
                        color = Black,
                        modifier = Modifier
                            .padding(start = 20.dp))
                }
                Row(modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(end = 15.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(R.drawable.settings_icon),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(32.dp))
                        Text(text = "Уведомления",
                            style = Theme.typography.title3Semibold,
                            color = Black,
                            modifier = Modifier
                                .padding(start = 20.dp))
                    }
                    Switch(
                        checked = state.notifications,
                        onCheckedChange = {
                            vm.onEvent(ProfileEvent.OnNotificationsChange)
                        },
                        modifier = Modifier
                            .size(48.dp, 28.dp),
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = Accent,
                            uncheckedTrackColor = InputIcon,
                            checkedThumbColor = White,
                            uncheckedThumbColor = White,
                            checkedBorderColor = Accent,
                            uncheckedBorderColor = InputIcon
                        )
                    )
                }
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Политика конфиденциальности",
                            style = Theme.typography.textMedium,
                            color = Placeholder,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable(interactionSource = MutableInteractionSource()){
                                    ripple()
                                    vm.onEvent(ProfileEvent.OpenPdf(context, 0, "privacy_policy.pdf"))
                                })
                        Text(text = "Пользовательское соглашение",
                            style = Theme.typography.textMedium,
                            color = Placeholder,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .clickable(interactionSource = MutableInteractionSource()) {
                                    ripple()
                                    vm.onEvent(ProfileEvent.OpenPdf(context, 0, "user_agreement.pdf"))
                                })
                        Text(text = "Выход",
                            style = Theme.typography.textMedium,
                            color = Error,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 24.dp)
                                .clickable(interactionSource = MutableInteractionSource()) {
                                    ripple()
                                    vm.onEvent(ProfileEvent.OnExit)
                                    navController.navigate(Navigation.SingIn){
                                        popUpTo(0)
                                    }
                                })
                    }
                }
            }
        }
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){
            TabBar(
                currentScreenNumber = 4,
                modifier = Modifier.padding(horizontal = 8.dp),
                onFirstIconCLick = {navController.navigate(Navigation.Main)},
                onSecondIconCLick = {navController.navigate(Navigation.Catalog)},
                onThirdIconCLick = { navController.navigate(Navigation.Projects) },
                onFourthIconCLick = {}
            )
        }
        if(state.showPdf){
            var scale by remember { mutableStateOf(1f) }
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(White)
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, _ ->
                         scale *= zoom
                    }
                }
            ){
                IconButton(
                    onClick = {
                        vm.onEvent(ProfileEvent.ClosePdf)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 15.dp)
                        .padding(end = 15.dp)
                ) {
                    Icon(painter = painterResource(com.example.uikit.R.drawable.icon_dismiss),
                        contentDescription = null,
                        tint = Color.Unspecified)
                }
                state.pdfBitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer(
                                scaleX = scale,
                                scaleY = scale
                            )
                    )
                }
            }
        }
    }
}