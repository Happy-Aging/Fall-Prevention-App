package com.appname.happyAging.presentation.user.view

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import com.appname.happyAging.presentation.common.component.CustomTextFieldWithTitle
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
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

enum class Sex(val english:String){
    MALE("male"),
    FEMALE("female"),
}
enum class SignupType(val english: String){
    INDIVIDUAL("individual"),
    CARE_MANAGER("careManager"),
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
    var sex by rememberSaveable { mutableStateOf(Sex.MALE) }
    var signupType by rememberSaveable { mutableStateOf(SignupType.INDIVIDUAL) }

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
            CustomTextFieldWithTitle(text = "이름", value = userName, onValueChange = {userName = it})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(text = "아이디", value = id, onValueChange = {id = it})
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(
                text = "비밀번호",
                value = password,
                onValueChange = {password = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction =  ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CustomTextFieldWithTitle(
                text = "비밀번호 재확인",
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
            CustomTextFieldWithTitle(text = "상세주소", value = detailAddress, onValueChange = {
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
            RadioButtonRow(text = "남자", value = sex == Sex.MALE, id = 0, onClick = {
                sex = Sex.MALE
            })
            RadioButtonRow(text = "여자", value = sex == Sex.FEMALE, id = 0, onClick = {
                sex = Sex.FEMALE
            })
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            Text(
                text = "가입자 유형",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            RadioButtonRow(text = "일반", value = signupType == SignupType.INDIVIDUAL, id = 0, onClick = {
                signupType = SignupType.INDIVIDUAL
            })
            RadioButtonRow(text = "돌봄매니저", value = signupType == SignupType.CARE_MANAGER, id = 0, onClick = {
                signupType = SignupType.CARE_MANAGER
            })
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CommonButton(text = "계정 만들기") {
                navController.navigateMain()
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
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

