package com.appname.happyAging.presentation.user.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.layout.DefaultLayout

@Composable
fun LoginScreen(navController: NavController) {
    DefaultLayout {
        Text(text ="LoginScreen")
    }
}