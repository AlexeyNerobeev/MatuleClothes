package com.example.matuleclothes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matuleclothes.presentation.CreateProfile.CreateProfileScreen
import com.example.matuleclothes.presentation.Main.MainScreen
import com.example.matuleclothes.presentation.OTP.OTPScreen
import com.example.matuleclothes.presentation.SignIn.SignInScreen
import com.example.matuleclothes.presentation.Splash.SplashScreen
import com.example.matuleclothes.ui.theme.MatuleClothesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatuleClothesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Navigation.Splash){
                    composable<Navigation.Splash> {
                        SplashScreen(navController)
                    }
                    composable<Navigation.SingIn> {
                        SignInScreen(navController)
                    }
                    composable<Navigation.Main> {
                        MainScreen(navController)
                    }
                    composable<Navigation.CreateProfile> {
                        CreateProfileScreen(navController)
                    }
                    composable<Navigation.OTP> {
                        OTPScreen(navController)
                    }
                }
            }
        }
    }
}