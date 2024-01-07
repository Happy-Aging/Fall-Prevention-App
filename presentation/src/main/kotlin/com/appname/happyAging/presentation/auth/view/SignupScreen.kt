package com.appname.happyAging.presentation.auth.view

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.appname.happyAging.domain.model.auth.SocialInfoModel
import com.appname.happyAging.domain.params.auth.SignupParams
import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.domain.params.auth.VendorType
import com.appname.happyAging.presentation.auth.viewmodel.AuthViewModel
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import kotlinx.coroutines.launch



@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun SignupScreen(
    onBackButton : () -> Unit = {},
    signupSuccess : () -> Unit = {},
    isKakaoSignup: Boolean = false,
    authViewModel: AuthViewModel = hiltViewModel(),
) {


    val context = LocalContext.current

    val emailFromVendor = authViewModel.socialInfo.collectAsState()

    var userName by rememberSaveable { mutableStateOf("") }
    var id by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordCheck by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }

    var smsCode by rememberSaveable { mutableStateOf("") }
    var userType by rememberSaveable { mutableStateOf(UserType.USER) }

    var userNameError: String? by rememberSaveable { mutableStateOf(null) }
    var idError: String? by rememberSaveable { mutableStateOf(null) }
    var passwordError: String? by rememberSaveable { mutableStateOf(null) }
    var passwordCheckError: String? by rememberSaveable { mutableStateOf(null) }
    var phoneNumberError: String? by rememberSaveable { mutableStateOf(null) }

    val coroutineComposeScope = rememberCoroutineScope()
    DefaultLayout(
        title = "회원가입",
        actions = {
            IconButton(onClick = onBackButton) {
                Icon(Icons.Default.Close, contentDescription = "취소")
            }
        }
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
            Text(
                text = "정보",
                style = TextStyles.TITLE_MEDIUM2,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = Sizes.INTERVAL1),
            )
            CustomTextEditField(
                label = "이름을 입력하세요",
                value = userName,
                onValueChange = { userName = it })
            userNameError?.let {
                Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = it,
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Color.Red
                    ),
                    textAlign = TextAlign.Left,
                )
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            if(!isKakaoSignup){

                CustomTextEditField(label = "메일을 입력하세요", value = id, onValueChange = { id = it })
                idError?.let {
                    Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
                    Text(
                        modifier = Modifier
                            .align(Alignment.Start),
                        text = it,
                        style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                            color = Color.Red
                        ),
                        textAlign = TextAlign.Left,
                    )
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(
                    label = "비밀번호를 입력하세요",
                    value = password,
                    onValueChange = { password = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )
                passwordError?.let {
                    Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
                    Text(
                        modifier = Modifier
                            .align(Alignment.Start),
                        text = it,
                        style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                            color = Color.Red
                        ),
                        textAlign = TextAlign.Left,
                    )
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
                CustomTextEditField(
                    label = "비밀번호을 한 번 더 입력하세요",
                    value = passwordCheck,
                    onValueChange = { passwordCheck = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                )
                passwordCheckError?.let {
                    Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
                    Text(
                        modifier = Modifier
                            .align(Alignment.Start),
                        text = it,
                        style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                            color = Color.Red
                        ),
                        textAlign = TextAlign.Left,
                    )
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            }

            Box(
                contentAlignment = Alignment.CenterEnd,
            ) {
                CustomTextEditField(
                    label = "휴대폰 번호을 입력하세요", value = phoneNumber,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next,
                    ),
                    onValueChange = { phoneNumber = it })
                Text(
                    text = "인증번호 받기",
                    style = TextStyles.CONTENT_SMALL0_STYLE,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(bottom = Sizes.INTERVAL1)
                        .clickable {

                        },
                )
            }
            phoneNumberError?.let {
                Spacer(modifier = Modifier.height(Sizes.INTERVAL3))
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = it,
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Color.Red
                    ),
                    textAlign = TextAlign.Left,
                )
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
            ) {
                CustomTextEditField(
                    label = "인증번호",
                    value = smsCode,
                    onValueChange = { smsCode = it })
                Text(
                    text = "인증번호 확인",
                    style = TextStyles.CONTENT_SMALL0_STYLE,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(bottom = Sizes.INTERVAL1)
                        .clickable {
//                            val credential =
//                                PhoneAuthProvider.getCredential(verificationId, smsCode)
//                            signInWithPhoneAuthCredential(activity!!, credential)
                        },
                )
            }

            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "가입자 유형",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier.weight(2f),
                )
                //Spacer(modifier = Modifier.width(Sizes.INTERVAL_LARGE3))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.weight(5f),
                ) {

                    UserType.values().forEach { type ->
                        RadioButtonRow(text = type.korean, value = userType == type, id = 0,
                            onClick = {
                                userType = type
                            })
                        Spacer(modifier = Modifier.width(Sizes.INTERVAL_MEDIUM))
                    }
                }
            }
            Row {
                Spacer(modifier = Modifier.weight(2f))
                Text(
                    text = "*돌봄매니저란?\n해피에이징 소속 매니저입니다.",
                    style = TextStyles.CONTENT_SMALL0_STYLE.copy(
                        color = Colors.GREY_TEXT
                    ),
                    modifier = Modifier
                        .weight(5f)
                        .padding(bottom = Sizes.INTERVAL1),
                )
            }

            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            CommonButton(text = "계정 만들기") {
                userNameError = if (userName.isEmpty()) {
                    "이름을 입력해주세요"
                } else {
                    null
                }
                phoneNumberError = if (phoneNumber.isEmpty()) {
                    "휴대폰 번호를 입력해주세요"
                } else if(!Patterns.PHONE.matcher(phoneNumber).matches()){
                    "휴대폰 번호가 올바르지 않습니다"
                }
                else {
                    null
                }

                if(isKakaoSignup){

                    if (userNameError != null || phoneNumberError != null) {
                        return@CommonButton
                    }
                    val signupParams = SignupParams(
                        name = userName,
                        email = (emailFromVendor.value!! as SocialInfoModel.Progress).email,
                        password = null,
                        phoneNumber = phoneNumber,
                        type = userType,
                        vendor = VendorType.KAKAO
                    )
                    coroutineComposeScope.launch{
                        val resp = authViewModel.signup(signupParams, context)
                        if(resp){
                            signupSuccess()

                        }
                    }
                    return@CommonButton
                }




                idError = if (id.isEmpty()) {
                    "이메일을 입력해주세요"
                } else if(!Patterns.EMAIL_ADDRESS.matcher(id).matches()){
                    "이메일 형식이 올바르지 않습니다"
                } else {
                    null
                }
                passwordError = if (password.isEmpty()) {
                    "비밀번호를 입력해주세요"
                } else {
                    null
                }
                passwordCheckError = if (passwordCheck.isEmpty()) {
                    "비밀번호를 한 번 더 입력해주세요"
                } else if(passwordCheck != password){
                    "비밀번호가 일치하지 않습니다"
                } else{
                    null
                }
                if (userNameError != null || idError != null || passwordError != null || passwordCheckError != null || phoneNumberError != null) {
                    return@CommonButton
                }

                val signupParams = SignupParams(
                    name = userName,
                    email = id,
                    password = password,
                    phoneNumber = phoneNumber,
                    type = userType,
                    vendor = VendorType.HAPPY_AGING
                )
                coroutineComposeScope.launch{
                    val resp = authViewModel.signup(signupParams, context)
                    if(resp){
                        signupSuccess()
                    }
                }
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
        }
    }


}

@Composable
@Preview
fun SignupScreenPreview() {
    SignupScreen()
}

