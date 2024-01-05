package com.appname.happyAging.presentation.senior.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.CreateSeniorParams
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Colors
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.senior.viewmodel.SeniorViewModel
import java.time.LocalDate


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateSeniorScreen(
    navController: NavController,
) {
    val parentEntry = remember { navController.getBackStackEntry(BottomNavRouter.SENIOR_LIST.routePath) }
    val viewModel : SeniorViewModel = hiltViewModel(parentEntry)
    DefaultLayout(
        title = Router.SENIOR_CREATE.korean,
        actions = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(Icons.Default.Close, contentDescription = "취소")
            }
        }
    ) {
        var seniorName by rememberSaveable { mutableStateOf("") }
        var relation by rememberSaveable { mutableStateOf(RelationWithSenior.SELF) }
        var address : String? by rememberSaveable { mutableStateOf(null) }
        var detailAddress by rememberSaveable { mutableStateOf("") }
        var seniorPhoneNumber by rememberSaveable { mutableStateOf("") }
        var birth by rememberSaveable { mutableStateOf("") }
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
            CustomTextEditField(label = "이름을 입력하세요", value = seniorName , onValueChange = {seniorName = it} )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
            CustomTextEditField(label = "거주지를 입력하세요", value = address?: "" , onValueChange = {address = it} )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
            CustomTextEditField(label = "출생년도를 입력하세요", value = birth , onValueChange = {birth = it} )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
            CustomTextEditField(
                label = "휴대폰 번호를 입력하세요(선택사항)",
                value = seniorPhoneNumber ,
                onValueChange = {seniorPhoneNumber = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
            )
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
            RelationWithSenior.values().forEach {relationWithSenior ->
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
                if(seniorName.isEmpty() || address.isNullOrEmpty() || birth.isEmpty()) {
                    return@CommonButton
                }
                val birthDate = LocalDate.of(birth.toInt(), 1, 1)
                val fullAddress = "$address $detailAddress"
                val params = CreateSeniorParams(
                    name = seniorName,
                    address = fullAddress,
                    birth = birthDate,
                    phoneNumber = seniorPhoneNumber.ifEmpty { null },
                    relation = relation,
                    )
                viewModel.createSenior(params)
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
        }
    }
}

@Preview
@Composable
fun PreviewCreateSeniorScreen() {
    CreateSeniorScreen(navController = NavController(LocalContext.current))
}