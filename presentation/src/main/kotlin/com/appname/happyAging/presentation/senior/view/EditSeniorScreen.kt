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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.appname.happyAging.domain.model.senior.RelationWithSenior
import com.appname.happyAging.domain.params.senior.UpdateSeniorParams
import com.appname.happyAging.presentation.common.component.CommonButton
import com.appname.happyAging.presentation.common.component.CustomTextEditField
import com.appname.happyAging.presentation.common.component.RadioButtonRow
import com.appname.happyAging.presentation.common.constant.Sizes
import com.appname.happyAging.presentation.common.constant.TextStyles
import com.appname.happyAging.presentation.common.layout.DefaultLayout
import com.appname.happyAging.presentation.common.navigation.BottomNavRouter
import com.appname.happyAging.presentation.common.navigation.Router
import com.appname.happyAging.presentation.common.state.UiState
import com.appname.happyAging.presentation.senior.viewmodel.SeniorViewModel


//CreateSeniorScreen.kt과 거의 동일하긴하나
//명확한 역할분리를 위해 코드 중복이지만 별도의 파일로 분리하였습니다.
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun EditSeniorScreen(
    navController: NavController,
    id: Long,
) {
    val parentEntry = remember { navController.getBackStackEntry(BottomNavRouter.SENIOR_LIST.routePath) }
    val viewModel : SeniorViewModel = hiltViewModel(parentEntry)
    val state = viewModel.senior.collectAsState()
    val senior = (state.value as UiState.Success).data.find { it.id == id }!!
    DefaultLayout(
        title = Router.SENIOR_EDIT.korean,
        actions = {
            IconButton(onClick = { navController.popBackStack()}) {
                Icon(Icons.Default.Close, contentDescription = "취소")
            }
        }
    ) {
        var seniorName by rememberSaveable { mutableStateOf(senior.name) }
        var relation by rememberSaveable { mutableStateOf(senior.relation) }
        var address by rememberSaveable { mutableStateOf(senior.address) }
        var detailAddress by rememberSaveable { mutableStateOf("") }
        var seniorPhoneNumber by rememberSaveable { mutableStateOf(senior.phoneNumber ?: "") }
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
            CustomTextEditField(label = "거주지를 입력하세요", value = address , onValueChange = {address = it} )
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM2))
            CustomTextEditField(label = "상세주소", value = detailAddress , onValueChange = {detailAddress = it} )
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
//            Text(
//                text = "앱 가입자와 낙상 위험도 조사 받을 분의 관계를 선택하세요",
//                style = TextStyles.CONTENT_TEXT3_STYLE.copy(
//                    color = Colors.GREY_TEXT
//                ),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(bottom = Sizes.INTERVAL1),
//            )
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
                text = "저장하기",
            ) {
                val params = UpdateSeniorParams(
                    id = id,
                    name = seniorName,
                    address = address,
                    relation = relation,
                )
                viewModel.updateSenior(params)
                navController.popBackStack()
            }
            Spacer(modifier = Modifier.height(Sizes.INTERVAL_MEDIUM))
        }
    }
}