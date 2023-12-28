package com.appname.happyAging.presentation.user.view

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextFieldWithTitle
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.navigateMain

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
fun SignupScreen(
    navController: NavController,
    isKakaoSignup: Boolean = false,
){
    DefaultLayout(
        title = "회원가입",
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Sizes.DEFAULT_PADDING,
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
            CustomTextFieldWithTitle(text = "이름", value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(text = "아이디", value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(text = "비밀번호", value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(text = "비밀번호 재확인", value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))

            Text(
                text = "주소",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            CommonButton(
                text = "",
                color = Color.Transparent,
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Colors.DIVIDER_GREY,
                    shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND)
                )
            ) {
                //주소
            }

            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(text = "상세주소", value = "", onValueChange = {})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))


            Text(
                text = "출생년도",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))


            Text(
                text = "성별",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))

            Text(
                text = "가입자 유형",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CommonButton(text = "계정 만들기") {
                navController.navigateMain()
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
        }
    }
}

@Composable
@Preview
fun SignupScreenPreview() {
    SignupScreen(navController = NavController(LocalContext.current))
}

