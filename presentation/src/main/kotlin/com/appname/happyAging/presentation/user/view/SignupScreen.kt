package com.appname.happyAging.presentation.user.view

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.CustomTextFieldWithTitle
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.LoginRouter
import com.appname.happyAging.presentation.common.navigation.go
import com.appname.happyAging.presentation.common.navigation.navigateMain
import java.time.LocalDate

object SignupScreenFactory

@Composable
fun SignupScreenFactory.emailSignup(navController: NavController) {
    SignupScreen(navController =navController)
}

@Composable
fun SignupScreenFactory.kakaoSignup(navController: NavController) {
    SignupScreen(navController =navController)
}

enum class Sex(val english:String, val korean: String){
    MALE("male", "남자"),
    FEMALE("female", "여자"),
}
enum class SignupType(val english: String, val korean: String){
    INDIVIDUAL("individual", "일반"),
    CARE_MANAGER("careManager", "돌봄매니저"),
}

@Composable
fun SignupScreen(
    navController: NavController,
    isKakaoSignup: Boolean = false,
){
    var userName by rememberSaveable { mutableStateOf("") }
    var id by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordCheck by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var detailAddress by rememberSaveable { mutableStateOf("") }
    var birthYear by rememberSaveable { mutableStateOf("") }
    var sexType by rememberSaveable { mutableStateOf(Sex.MALE) }
    var signupType by rememberSaveable { mutableStateOf(SignupType.INDIVIDUAL) }


    var openAlertDialog by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize()
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
                CustomTextEditField(label = "이름을 입력하세요", value = userName, onValueChange = {userName = it})
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(label = "메일을 입력하세요", value = id, onValueChange = {id = it})
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(
                    label = "비밀번호를 입력하세요",
                    value = password,
                    onValueChange = {password = it},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction =  ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(
                    label = "비밀번호을 한 번 더 입력하세요",
                    value = passwordCheck,
                    onValueChange = {passwordCheck = it},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction =  ImeAction.Next
                    ),
                )
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))

                Text(
                    text = "주소",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )
                CommonButton(
                    text = address,
                    color = Color.Transparent,
                    textColor = Colors.BLACK,
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = Colors.DIVIDER_GREY,
                        shape = RoundedCornerShape(size = Sizes.BUTTON_ROUND)
                    )
                ) {
                    openAlertDialog = true
                }

                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(label = "상세주소", value = detailAddress, onValueChange = {
                    detailAddress = it
                })
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))


                Text(
                    text = "출생년도",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )

                ScrollableYears()
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                Text(
                    text = "성별",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )
                Sex.values().forEach {sex ->
                    RadioButtonRow(text = sex.korean, value = sexType == sex, id = 0, onClick = {
                        sexType = sex
                    })
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                Text(
                    text = "가입자 유형",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )
                SignupType.values().forEach {type ->
                    RadioButtonRow(text = type.korean, value = signupType == type, id = 0, onClick = {
                        signupType = type
                    })
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CommonButton(text = "계정 만들기") {
                    navController.navigateMain()
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            }
        }
        if(openAlertDialog){
            AddressWebView(
                onSelected = {
                    Log.d("onSelected", "onSelected $it from SignupScreen")
                    address = it
                    openAlertDialog = false
                }
            )
        }
    }


}
@Composable
fun ScrollableYears() {
    val scrollState = rememberScrollState()
    val years = LocalDate.now().year
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(Sizes.BUTTON_HEIGHT),
        flingBehavior = ScrollableDefaults.flingBehavior()
    ) {
        items(100) { index ->
            Text("${years - index}")
        }
    }
}


@Composable
@Preview
fun SignupScreenPreview() {
    SignupScreen(navController = NavController(LocalContext.current))
}

