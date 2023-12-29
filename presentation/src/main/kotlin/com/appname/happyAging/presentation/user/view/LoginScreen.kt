package com.appname.happyAging.presentation.user.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.R
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.LoginRouter
import com.appname.happyAging.presentation.common.navigation.go
import com.appname.happyAging.presentation.common.navigation.navigateMain
import com.appname.happyAging.presentation.user.component.KakaoButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    DefaultLayout(
        title = LoginRouter.LOGIN.korean,
    ) {
        var id by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = Sizes.DEFAULT_PADDING,
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopPart()
            ////////////////////////////
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
            Text(
                text = "아이디",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
            CustomTextEditField(
                value = id,
                onValueChange = { id = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction =  ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL1))
            Text(
                text = "비밀번호",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier.align(Alignment.Start),
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
            CustomTextEditField(
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction =  ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        login(navController, id, password)
                    }
                )
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
            CommonButton(
                text = "로그인",
                color = Colors.WHITE,
                textColor = Colors.BLACK,
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Colors.DIVIDER_GREY,
                    shape = RoundedCornerShape(
                        size = Sizes.BUTTON_ROUND
                    )
                )
            ) {
                login(navController, id, password)
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL1))
            Text(
                text = "회원가입",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.PRIMARY_ORANGE
                ),
                modifier = Modifier.align(Alignment.Start)
                    .clickable {
                         navController.go(LoginRouter.EMAIL_SIGNUP)
                    },
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL2))
            Text(
                text = "ID/비밀번호 찾기",
                style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                    color = Colors.PRIMARY_ORANGE
                ),
                modifier = Modifier.align(Alignment.Start),
            )
            Row {
                //Line
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    thickness = 1.dp,
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = Sizes.INTERVAL1),
                    text = "또는",
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Colors.GREY_TEXT
                    ),
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    thickness = 1.dp,
                )
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
            KakaoButton(text = "카카오 로그인") {
                navController.go(LoginRouter.KAKAO_SIGNUP)
            }
        }
    }
}

private fun login(
    navController: NavController,
    id: String,
    password: String
) {
    navController.navigateMain()
}

@Composable
fun TopPart() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE1))
        Image(
            modifier = Modifier
                .size(71.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.happy_aging_icon),
            contentDescription = "해피에이징",
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL1))
        Text(
            text = "해피에이징",
            style = TextStyles.TITLE_LARGE2,
        )
        Spacer(modifier = Modifier.height(Sizes.INTERVAL2))
        Text(
            text = "소개말",
            style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                color = Colors.GREY_TEXT
            ),
            textAlign = TextAlign.Left,
        )

    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}