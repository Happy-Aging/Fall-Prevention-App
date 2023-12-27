package com.appname.happyAging.presentation.user.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.layout.DefaultLayout

object SignupScreenFactory

@Composable
fun SignupScreenFactory.emailSignup(navController: NavController) {
    SignupScreen(navController =navController)
}

@Composable
fun SignupScreenFactory.kakaoSignup(navController: NavController) {
    SignupScreen(navController =navController)
}

@Composable
fun SignupScreen(navController: NavController){
    DefaultLayout {
        Text(text ="SignupScreen")
    }
}


