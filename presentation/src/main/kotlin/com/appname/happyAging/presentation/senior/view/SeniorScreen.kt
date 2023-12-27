package com.appname.happyAging.presentation.senior.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter

@Composable
fun SeniorScreen(
    navController: NavController,
) {
    DefaultLayout(
        title = BottomNavRouter.SENIOR_LIST.korean,
    ) {
        Text(text = "시니어 추가하기")
    }
}