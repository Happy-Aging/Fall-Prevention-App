package com.appname.happyAging.presentation.user.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.LoginRouter
import com.appname.happyAging.presentation.common.navigation.navigateKakaoSignup
import com.appname.happyAging.presentation.common.navigation.navigateMain
import com.appname.happyAging.presentation.user.component.KakaoButton

@Composable
fun LoginScreen(navController: NavController) {
    DefaultLayout(
        title = LoginRouter.LOGIN.korean,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Sizes.DEFAULT_PADDING,
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "해피에이징",
                style = TextStyles.TITLE_LARGE2,
            )
            Spacer(modifier =Modifier.height(Sizes.INTERVAL2))
            Text(
                text = "소개말",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT
                ),
                textAlign = TextAlign.Left,
            )
            Text(
                text = "회원가입",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT
                ),
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier =Modifier.height(Sizes.INTERVAL4))
            Text(
                text = "ID/비밀번호 찾기",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.GREY_TEXT
                ),
                modifier = Modifier.align(Alignment.Start),
            )
            TextButton(onClick = {
                navController.navigateMain()
            }) {
                Text(text = "로그인")
            }
            KakaoButton(text = "카카오 로그인") {
                navController.navigateMain()
                //navController.navigateKakaoSignup()
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}