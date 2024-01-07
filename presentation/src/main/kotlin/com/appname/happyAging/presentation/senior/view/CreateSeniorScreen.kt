package com.appname.happyAging.presentation.senior.view

import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.presentation.auth.view.AddressWebView
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.common.utils.noRippleClickable
import com.appname.happyAging.presentation.senior.viewmodel.SeniorViewModel
import java.time.LocalDate


@Composable
fun CreateSeniorScreen(
    backAction : () -> Unit = {},
    seniorViewModel: SeniorViewModel = hiltViewModel(),
) {
    DefaultLayout(
        title = Router.SENIOR_CREATE.korean,
        actions = {
            IconButton(onClick = backAction) {
                Icon(Icons.Default.Close, contentDescription = "취소")
            }
        }
    ) {

        var isAddressWebViewOpen by rememberSaveable { mutableStateOf(false) }

        var seniorName by rememberSaveable { mutableStateOf("") }
        var relation by rememberSaveable { mutableStateOf(RelationWithSenior.SELF) }
        var address by rememberSaveable { mutableStateOf("") }
        var seniorPhoneNumber by rememberSaveable { mutableStateOf("") }
        var birth by rememberSaveable { mutableStateOf("") }

        var seniorNameError : String? by rememberSaveable { mutableStateOf(null) }
        var addressError : String? by rememberSaveable { mutableStateOf(null) }
        var birthError : String? by rememberSaveable { mutableStateOf(null) }
        var seniorPhoneNumberError : String? by rememberSaveable { mutableStateOf(null) }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = Sizes.DEFAULT_PADDING,
                ),
            ) {
                Spacer(modifier = Modifier.padding(Sizes.INTERVAL_LARGE4))
                Text(
                    text = "정보",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL0),
                )
                CustomTextEditField(
                    label = "이름을 입력하세요",
                    isError = seniorNameError != null,
                    value = seniorName,
                    onValueChange = { seniorName = it })
                seniorNameError?.let {
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
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))

                Box(
                    modifier = Modifier
                        .height(Sizes.BUTTON_HEIGHT)
                        .fillMaxWidth()
                ) {
                    CustomTextEditField(
                        isError = addressError != null,
                        value = address,
                        onValueChange = {
                            address = it
                        })
                    if(!isAddressWebViewOpen && address.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize()
                                .noRippleClickable {
                                    isAddressWebViewOpen = true
                                },
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "거주지를 입력하세요",
                                style = TextStyles.CONTENT_TEXT3_STYLE.copy(
                                    color = Colors.GREY_TEXT
                                ),
                                modifier = Modifier
                                    .padding(start = Sizes.INTERVAL1),
                            )
                        }

                    }
                }
                addressError?.let {
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
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
                CustomTextEditField(
                    isError = birthError != null,
                    label = "출생년도를 입력하세요",
                    value = birth,
                    onValueChange = { birth = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                )
                birthError?.let {
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
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
                CustomTextEditField(
                    isError = seniorPhoneNumberError != null,
                    label = "휴대폰 번호를 입력하세요(선택사항)",
                    value = seniorPhoneNumber,
                    onValueChange = { seniorPhoneNumber = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                )
                seniorPhoneNumberError?.let {
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
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE4))
                Text(
                    text = "관계",
                    style = TextStyles.TITLE_MEDIUM2,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL4),
                )
                Text(
                    text = "앱 가입자와 낙상 위험도 조사 받을 분의 관계를 선택하세요",
                    style = TextStyles.CONTENT_TEXT3_STYLE.copy(
                        color = Colors.GREY_TEXT
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = Sizes.INTERVAL1),
                )
                RelationWithSenior.values().forEach { relationWithSenior ->
                    RadioButtonRow(
                        text = relationWithSenior.korean,
                        value = relation == relationWithSenior, id = 0,
                        onClick = {
                            relation = relationWithSenior
                        }
                    )
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_LARGE3))
                CommonButton(
                    text = "생성하기",
                ) {
                    seniorNameError = if (seniorName.isEmpty()) {
                        "이름을 입력해주세요"
                    } else {
                        null
                    }
                    addressError = if (address.isEmpty()) {
                        "거주지를 입력해주세요"
                    } else {
                        null
                    }
                    birthError = if (birth.isEmpty()) {
                        "출생년도를 입력해주세요"
                    } else {
                        val birthParse = try {
                            birth.toInt()
                        } catch (e: Exception) {
                            null
                        }
                        if (birthParse == null || birthParse < 1900 || birthParse > LocalDate.now().year) {
                            "출생년도를 올바르게 입력해주세요"
                        } else {
                            null
                        }
                    }
                    seniorPhoneNumberError = if (!Patterns.PHONE.matcher(seniorPhoneNumber).matches() && seniorPhoneNumber.isNotEmpty()) {
                        "휴대폰 번호 형식이 올바르지 않습니다"
                    } else {
                        null
                    }

                    if (seniorNameError != null || addressError != null || birthError != null || seniorPhoneNumberError != null) {
                        return@CommonButton
                    }
                    val birthDate = LocalDate.of(birth.toInt(), 1, 1)
                    val params = CreateSeniorParams(
                        name = seniorName,
                        address = address,
                        birth = birthDate,
                        phoneNumber = seniorPhoneNumber.ifEmpty { null },
                        relation = relation,
                    )
                    seniorViewModel.createSenior(params)
                    backAction()
                }
                Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
            }
            if(isAddressWebViewOpen) {
                AddressWebView(
                    onSelected = {
                        address = it
                        isAddressWebViewOpen = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCreateSeniorScreen() {
    CreateSeniorScreen()
}