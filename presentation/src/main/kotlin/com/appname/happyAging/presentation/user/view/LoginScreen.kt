package com.appname.happyAging.presentation.user.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.LoginRouter
import com.appname.happyAging.presentation.common.navigation.navigateMain

@Composable
fun LoginScreen(navController: NavController) {
    DefaultLayout(
        title = LoginRouter.LOGIN.korean,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "해피에이징",
                style = TextStyles.TITLE_LARGE2,
            )
            TextButton(onClick = {
                navController.navigateMain()
            }) {
                Text(text = "로그인")
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}