package com.appname.happyAging.presentation.user.view

import android.util.Log
import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.appname.happyAging.domain.params.auth.UserType
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.navigateMain
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

object SignupScreenFactory

@Composable
fun SignupScreenFactory.emailSignup(navController: NavController) {
    SignupScreen(navController =navController)
}

@Composable
fun SignupScreenFactory.kakaoSignup(navController: NavController) {
    SignupScreen(navController =navController)
}


val auth = Firebase.auth
var verificationId = ""
fun signInWithPhoneAuthCredential(activity: ComponentActivity,credential: PhoneAuthCredential) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                Log.d("signInWithCredential", "signInWithCredential:success")
            }
            else {
                Log.d("signInWithCredential", "signInWithCredential:failure")
            }
        }
}
@Composable
fun SignupScreen(
    navController: NavController,
    isKakaoSignup: Boolean = false,
){
    val activity = LocalContext.current as? ComponentActivity


    var userName by rememberSaveable { mutableStateOf("") }
    var id by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordCheck by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }

    var smsCode by rememberSaveable { mutableStateOf("") }
    var signupType by rememberSaveable { mutableStateOf(UserType.INDIVIDUAL) }


    //var openAlertDialog by rememberSaveable { mutableStateOf(false) }
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
                Text(
                    text = "정보",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )
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
                Box(
                    contentAlignment = Alignment.CenterEnd,
                ){
                    CustomTextEditField(
                        label = "휴대폰 번호을 입력하세요", value = phoneNumber,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction =  ImeAction.Next,
                        ),
                        onValueChange = {phoneNumber = it})
                    Text(
                        text = "인증번호 받기",
                        style = TextStyles.CONTENT_SMALL0_STYLE,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(bottom = Sizes.INTERVAL1)
                            .clickable {
                                Log.d("phone", "인증번호 받기 $phoneNumber")
                                val callbacks = object :
                                    PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

                                    override fun onVerificationFailed(e: FirebaseException) {
                                    }

                                    override fun onCodeSent(
                                        returnVerificationId: String,
                                        token: PhoneAuthProvider.ForceResendingToken
                                    ) {
                                        Log.d("onCodeSent", "onCodeSent $returnVerificationId")
                                        verificationId = returnVerificationId
                                    }
                                }

                                val optionsCompat = PhoneAuthOptions
                                    .newBuilder(auth)
                                    .setPhoneNumber("+8210$phoneNumber")
                                    .setTimeout(60L, TimeUnit.SECONDS)
                                    .setActivity(activity!!)
                                    .setCallbacks(callbacks)
                                    .build()
                                PhoneAuthProvider.verifyPhoneNumber(optionsCompat)

                            },
                    )
                }
                Box(
                    contentAlignment = Alignment.CenterEnd,
                ){
                    CustomTextEditField(label = "인증번호", value = smsCode, onValueChange = {smsCode = it})
                    Text(
                        text = "인증번호 확인",
                        style = TextStyles.CONTENT_SMALL0_STYLE,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(bottom = Sizes.INTERVAL1)
                            .clickable {
                                val credential =
                                    PhoneAuthProvider.getCredential(verificationId, smsCode)
                                signInWithPhoneAuthCredential(activity!!, credential)
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

                        UserType.values().forEach {type ->
                            RadioButtonRow(text = type.korean, value = signupType == type, id = 0,
                                onClick = {
                                    signupType = type
                                })
                            Spacer(modifier = Modifier.width(Sizes.INTERVAL_MEDIUM))
                        }
                    }
                }
                Row {
                    Spacer(modifier = Modifier.weight(2f))
                    Text(text = "*돌봄매니저란?\n해피에이징 소속 매니저입니다.",
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
                    navController.navigateMain()
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            }
        }
//        if(openAlertDialog){
//            AddressWebView(
//                onSelected = {
//                    Log.d("onSelected", "onSelected $it from SignupScreen")
//                    address = it
//                    openAlertDialog = false
//                }
//            )
//        }
    }


}
@Composable
@Preview
fun SignupScreenPreview() {
    SignupScreen(navController = NavController(LocalContext.current))
}

